create view v_prise_consommation_daily(id, date, month, year, consommation) as
SELECT p.id,
       date(pd.date)                                       AS date,
       EXTRACT(month FROM pd.date)::integer                AS month,
       EXTRACT(year FROM pd.date)::bigint                  AS year,
       COALESCE(sum(pd.consommation), 0::double precision) AS consommation
FROM prise_data pd
         RIGHT JOIN prise p ON p.id = pd.prise_id
GROUP BY p.id, (date(pd.date)), (EXTRACT(month FROM pd.date)), (EXTRACT(year FROM pd.date));


create view v_customer_count_monthly(count, month, year) as
SELECT count(*)                                AS count,
       EXTRACT(month FROM c.registration_date) AS month,
       EXTRACT(year FROM c.registration_date)  AS year
FROM customer c
GROUP BY (EXTRACT(month FROM c.registration_date)), (EXTRACT(year FROM c.registration_date));


create view v_panel_production_usage_monthly(production, month, year, id) as
SELECT COALESCE(sum(panneau_data.production), 0::double precision) AS production,
       EXTRACT(month FROM panneau_data.date)::integer              AS month,
       EXTRACT(year FROM panneau_data.date)::bigint                AS year,
       panneau_data.panneau_id                                     AS id
FROM panneau_data
         RIGHT JOIN panneau ON panneau_data.panneau_id = panneau.id
GROUP BY (EXTRACT(month FROM panneau_data.date)), (EXTRACT(year FROM panneau_data.date)), panneau_data.panneau_id;


create view v_panel_production_usage_daily(production, id, date) as
SELECT COALESCE(sum(panneau_data.production), 0::double precision) AS production,
       panel.id,
       date(panneau_data.date)                                     AS date
FROM panneau_data
         RIGHT JOIN panneau panel ON panneau_data.panneau_id = panel.id
GROUP BY (date(panneau_data.date)), panel.id;

