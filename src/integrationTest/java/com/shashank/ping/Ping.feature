Feature: To ping the service

  Scenario: When I invoke the ping API, it shall respond back with pong
    When I invoke the "ping" api
    Then I shall get "pong" as response