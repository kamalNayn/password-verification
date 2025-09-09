# Password Verification

Password Verification is java based project which verify the password.

Implemented following rules, will throw exception with message if this rule failed.

- Password should be larger than 8 chars  
- password should not be null  
- password should have one uppercase letter at least  
- password should have one lowercase letter at least  
- password should have one number at least  

Password is OK if at least three conditions is true and must have one lowercase letter.

Optimized by validating each rules parallely.


## Usage

```java
PasswordValidator.validate(password);

