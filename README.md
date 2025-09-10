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
                new NotNullRule(),
                new LowercaseRule(),
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
                );
        PasswordValidator passwordValidator = new PasswordValidator(rules, 3);

        String[] passwords = {
                "abc",
                "Abc124",
                "password123",
                "12345678"
        };
        for (String password : passwords) {
            System.out.println("🔑 Testing password: " + password);
            try {
                boolean isValid = passwordValidator.validate(password);
                if (isValid) {
                    System.out.println("✅ Password is valid!\n");
                }
            } catch (IncorrectPasswordException ex) {
                System.out.println("❌ Password validation failed:");
                ex.getErrorMessages().forEach(msg -> System.out.println("   - " + msg));
                System.out.println();
            }
        }

