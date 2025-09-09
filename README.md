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
List<PasswordRule> rules = List.of(
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
                );
        PasswordRule lowerCaseRule = new LowercaseRule();
        PasswordValidator passwordValidator = new PasswordValidator(rules, lowerCaseRule, 3);

        String[] passwords = {
                "abc",
                "Abc124",
                "password123",
                "12345678"
        };
        for(String password: passwords){
            ValidationResult validationResult = passwordValidator.validatePassword(password);
            if(validationResult.isValid()){
                System.out.println("Password: " +password+" is valid!");
            }else{
                System.out.println("Password: " +password+" is Invalid! Below failed rules:\n"
                        +String.join("\n", validationResult.getFailedMessages()));
            }
        }


