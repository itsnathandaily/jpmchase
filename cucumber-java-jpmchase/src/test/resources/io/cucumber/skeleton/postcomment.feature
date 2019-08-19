Feature: Backend Service Testing Task

  Scenario Outline: Make Posts and Comment
    Given I make a post with title <title> body <body> and userId <userId>
    And I make a comment for id 1
  Then I look at a list of posts

    Examples:
|title        |body   |userId |
|"foo"        |"bar"  |  1    |
|"another foo"|"boddy"|3      |
|"another foo"|"boddy"| 1     |
