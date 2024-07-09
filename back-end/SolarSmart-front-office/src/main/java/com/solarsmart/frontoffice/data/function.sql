CREATE OR REPLACE FUNCTION get_time_usage_monthly(p_year INT, p_battery_id INT)
    RETURNS TABLE (
                      battery_id INT,
                      month INT,
                      total_usage NUMERIC,
                      year INT
                  ) AS $$
DECLARE
    v_current_data RECORD;
    v_previous_data RECORD := NULL;
    v_duration INTERVAL;
    v_hours NUMERIC;
    v_year_month DATE;
BEGIN
    -- Suppression de la table temporaire si elle existe déjà
    IF EXISTS (SELECT FROM pg_tables WHERE tablename = 'usage_hours_by_month') THEN
        DROP TABLE usage_hours_by_month;
    END IF;
    -- Création d'une table temporaire pour stocker les heures d'utilisation par mois
    CREATE TEMP TABLE usage_hours_by_month (year_month DATE, hours NUMERIC);

    FOR v_current_data IN (
        SELECT bd.date, bd.courant
        FROM battery_data bd
        WHERE EXTRACT(YEAR FROM bd.date) = p_year AND bd.battery_id = p_battery_id
        ORDER BY bd.date
    )
        LOOP
            IF v_previous_data IS NOT NULL THEN
                IF v_current_data.courant != 0 AND v_previous_data.courant != 0 THEN
                    IF v_current_data.date::DATE = v_previous_data.date::DATE THEN
                        v_duration := v_current_data.date - v_previous_data.date;
                        v_hours := EXTRACT(EPOCH FROM v_duration) / 3600;
                        v_year_month := DATE_TRUNC('MONTH', v_current_data.date)::DATE;

                        -- Mise à jour des heures d'utilisation pour le mois courant
                        UPDATE usage_hours_by_month
                        SET hours = COALESCE(hours, 0) + v_hours
                        WHERE year_month = v_year_month;

                        -- Si le mois courant n'est pas encore dans la table, l'ajouter
                        IF NOT FOUND THEN
                            INSERT INTO usage_hours_by_month (year_month, hours)
                            VALUES (v_year_month, v_hours);
                        END IF;
                    END IF;
                END IF;
            END IF;

            v_previous_data := v_current_data;
        END LOOP;

    RETURN QUERY
        SELECT
            p_battery_id AS battery_id,
            EXTRACT(MONTH FROM year_month)::INT AS month,
            hours AS total_usage,
            EXTRACT(YEAR FROM year_month)::INT AS year
        FROM usage_hours_by_month;

END; $$ LANGUAGE plpgsql;





CREATE OR REPLACE FUNCTION get_time_usage_daily(p_year INT, p_month INT, p_battery_id INT)
    RETURNS TABLE (
                      battery_id INT,
                      day DATE,
                      total_usage NUMERIC,
                      year INT
                  ) AS $$
DECLARE
    v_current_data RECORD;
    v_previous_data RECORD := NULL;
    v_duration INTERVAL;
    v_hours NUMERIC;
    v_year_day DATE;
BEGIN
    -- Suppression de la table temporaire si elle existe déjà
    IF EXISTS (SELECT FROM pg_tables WHERE tablename = 'usage_hours_by_day') THEN
        DROP TABLE usage_hours_by_day;
    END IF;

    -- Création d'une table temporaire pour stocker les heures d'utilisation par jour
    CREATE TEMP TABLE usage_hours_by_day (year_day DATE, hours NUMERIC);

    FOR v_current_data IN (
        SELECT bd.date, bd.courant
        FROM battery_data bd
        WHERE EXTRACT(YEAR FROM bd.date) = p_year AND EXTRACT(MONTH FROM bd.date) = p_month AND bd.battery_id = p_battery_id
        ORDER BY bd.date
    )
        LOOP
            IF v_previous_data IS NOT NULL THEN
                IF v_current_data.courant != 0 AND v_previous_data.courant != 0 THEN
                    v_duration := v_current_data.date - v_previous_data.date;
                    v_hours := EXTRACT(EPOCH FROM v_duration) / 3600;
                    v_year_day := v_current_data.date::DATE;

                    -- Mise à jour des heures d'utilisation pour le jour courant
                    UPDATE usage_hours_by_day
                    SET hours = COALESCE(hours, 0) + v_hours
                    WHERE year_day = v_year_day;

                    -- Si le jour courant n'est pas encore dans la table, l'ajouter
                    IF NOT FOUND THEN
                        INSERT INTO usage_hours_by_day (year_day, hours)
                        VALUES (v_year_day, v_hours);
                    END IF;
                END IF;
            END IF;

            v_previous_data := v_current_data;
        END LOOP;

    RETURN QUERY
        SELECT
            p_battery_id AS battery_id,
            year_day AS day,
            hours AS total_usage,
            EXTRACT(YEAR FROM year_day)::INT AS year
        FROM usage_hours_by_day;
END; $$ LANGUAGE plpgsql;


