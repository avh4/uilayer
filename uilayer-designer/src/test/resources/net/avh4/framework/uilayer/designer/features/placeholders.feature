Feature: creating a mockup with placeholders

Scenario: basic scene

    Given a new mockup
    When I add placeholders
    And I save the mockup
    Then I have java code that represents the mockup I designed

Scenario: empty scene

    Given a new mockup
    When I save the mockup
    Then I have java code that represents an empty scene
