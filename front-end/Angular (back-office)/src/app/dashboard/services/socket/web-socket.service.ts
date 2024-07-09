import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {webSocket, WebSocketSubject} from "rxjs/webSocket";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private batteryDataSubject: BehaviorSubject<any> = new BehaviorSubject(null);
  private socket$: WebSocketSubject<any>;
  private readonly socketUrl = 'http://localhost:8080/topic/batteryData';

  constructor() {
    this.socket$ = webSocket(this.socketUrl);
    this.socket$.subscribe(
        message => this.batteryDataSubject.next(message),
        err => console.error(err),
        () => console.warn('Completed!')
    );
  }

  public sendMessage(message: any): void {
    this.socket$.next(message);
  }

  public getMessages(): Observable<any> {
    return this.batteryDataSubject.asObservable();
  }

  public closeConnection(): void {
    this.socket$.complete();
  }
}
