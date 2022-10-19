# circuit-breaker with Resilience4j 

Configuration:

resilience4j:
  circuitbreaker:
    instances:
      test-service:
        registerHealthIndicator: true
        failureRateThreshold: 50
        minimumNumberOfCalls: 10
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED
        
        
failureRateThreshold: yüzde eşiği, %50 ye ulaşan hata durumunda OPEN durumuna alır.
minimumNumberOfCalls: Toplam cagrı sayısı, 10 cagrıdan 6 sı hata verir ise circuit-breaker acılır
waitDurationInOpenState: OPEN statusunden 5 sn sonra circuit_breaker 5 sn sonra HALF_OPEN durumuna gelir.
permittedNumberOfCallsInHalfOpenState HALF_OPEN durumunda 3 kez erişim sorunu yaşar ise kendisini yenide OPEN durumuna alır.



http://localhost:8088/actuator/health ile API'nin durumunu görebiliriz.

"circuitBreakers": {
    "status": "UNKNOWN",
    "details": {
    "test-service": {
    "status": "CIRCUIT_HALF_OPEN",
    "details": {
    "failureRate": "-1.0%",
    "failureRateThreshold": "50.0%",
    "slowCallRate": "-1.0%",
    "slowCallRateThreshold": "100.0%",
    "bufferedCalls": 0,
    "slowCalls": 0,
    "slowFailedCalls": 0,
    "failedCalls": 0,
    "notPermittedCalls": 0,
    "state": "HALF_OPEN"
    
bufferedCalls : toplam cagrı sayısı
failedCalls : fail cagrı sayısı
failureRate : yüzdesi
