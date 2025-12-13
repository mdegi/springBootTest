# Peer Review Checklist

## Functional Requirements

- [ ] All changes being deployed are defined in Monday Dev Tasks
- [ ] All tasks have been tagged with the correct version accordingly
- [ ] Code being reviewed meets functional requirements as documented in tasks

## Code changes
- [ ] Code being reviewed is readable, scalable, and appropriate use of comments have been made where applicable
- [ ] All code has been committed and branching strategy meets company policy
- [ ] All property values have been used in Vault as per company policy
- [ ] Unused property values, code variables, functions or commented methods have been removed
- [ ] All commits have been reviewed not just sections highlighted by the developer
- [ ] External libraries are updated to the latest stable versions, where possible
- [ ] Correct use of coding patterns styles has been made where applicable   
      _Factory / Decorator / Singleton etc ..._
- [ ] Check that no particular concern on performance or efficiency issues has been created  
      _Inefficient loops (Big O notation) / unneeded object creation / inefficient data structures (linkedList instead of ArrayList) etc ..._

## Logging / Documentation
- [ ] Logs have been implemented in strategic points and a span ID has been used where possible to make it easy to follow the path taken of a process
- [ ] Logs have been implemented using the company's policy and standards (example: - format / time / date / trace id / span id / etc ...)
- [ ] To our best judgement, no GDPR data has been included in logs   
    _Purpose Limitation / Anonymity / Security_
- [ ] Calls to external systems have been logged including the time of execution and time the response has been received
- [ ] Meaningful error messages are logged in exception handling scenarios, no unhandled exceptions should be thrown
- [ ] For new applications a Readme.md file is created 
- [ ] For existing applications, Readme.md file has been updated to reflect any changes that have been done or a new task to update documentation has been created where applicable

## Security
- [ ] No hard coded values / passwords or personal data have been committed
- [ ] Any stored passwords are encrypted using Company policy   
 _(currently using Jasypt for java applications)_
- [ ] For new applications, authentication, firewall access, and environment-specific restrictions have been taken into consideration
- [ ] Swagger Access authorisation has been updated on the server if applicable on the server

## DB changes
- [ ] No particular efficiency issues in SQL queries   
_(correct use of LIMIT and INNER JOIN etc ...)_
- [ ] All SQL statements have been tested
- [ ] Make sure SQL Injection restriction policy is in place


## Testing
- [ ] All requirements have been tested and verified to be working correctly
- [ ] Any other systems that may have been affected have been verified to work correctly
- [ ] Any load tests have been done if applicable
- [ ] No particular efficiency issues in SQL queries   
- [ ] Monday Dev task on UAT has been verified that all tests have passed successfully and confirmation from Project manager has been given   
 _(in circumstances where a change is being deployed and does not require UAT, a task on the DEV has been created showing that appropriate tests have been done)_
