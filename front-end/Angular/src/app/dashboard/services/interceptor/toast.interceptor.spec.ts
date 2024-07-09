import { TestBed } from '@angular/core/testing';

import { ToastInterceptor } from './toast.interceptor';

describe('ToastInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ToastInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ToastInterceptor = TestBed.inject(ToastInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
