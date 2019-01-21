# Mint ViewBinder ![](https://img.shields.io/bintray/v/syedowaisali/maven/mint-validator.svg)   [![GitHub issues](https://img.shields.io/github/issues/syedowaisali/mint-validator.svg)](https://github.com/syedowaisali/mint-validator/issues)   [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



- Validate using annotations and keep your code clean with repetitive if-else checks or Utility calls.
- Add validation markers (annotations) for checks like empty, length, email, or password combinations on EditText.
- You can even provide your own Regex to validate EditText data.



## Release
Available Version:  1.0.0 on [jcenter](https://bintray.com/syedowaisali/maven/mint-validator/1.0.0) 


## Library Source
[Jump to library source.](https://github.com/syedowaisali/mint-validator/tree/master/mint-validator/src/main/java/net/crystalapps/mint/validator/library)

## Getting Started
### Prerequisites

Minimum API Level is 21. 

### Adding the library


In your app level gradle **(4.0+)**, add:
```
    implementation 'net.crystalapps:mint-validator:1.0.0'
```
for gradle versions **below 4.0** use:
```
    compile 'net.crystalapps:mint-validator:1.0.0'
```
## Using in your project

- Consider an EditText for email validation:
```
    @Order(1)
    @NotEmpty(order = 1, message = R.string.empty_email_message)
    @ValidEmail(order = 2, message = R.string.invalid_email_message)
    @BindView(R.id.emailEditText)
    private EditText emailEditText;

    @Order(2)
    @NotEmpty(order = 1, message = R.string.password_empty_message)
    @MinLength(order = 2, value = 6, message = R.string.password_min_message)
    @MaxLength(order = 3, value = 20, message = R.string.password_max_message)
    @BindView(R.id.passwordEditText)
    private EditText passwordEditText;
```    

- `@BindView` is must to use these validations, see [ViewBinder](https://github.com/syedowaisali/mint-view-binder) library for more.

- Top level Order annotation describe which EditText will be validated in order number you provide, here, emailEditText will be verified first then passwordEditText.

- `@ValidEmail` runs email regex on input, `message` variable in annotation takes a message string resource, which will be shown when validation fails.

- `order` variable in annotation sets in which order annotations on a view will be validated. If a view like email has multiple validations, this order will be used to describe in which sequence these validations will be executed on that single view.

- `value` variable in annotation will describe any value if the given annotation needs anything, like MinLength will specify minimum length in this variable, similarly max length will specify maximum length in integer.

- Use `@Regex` annotation to provide your own regex implementation.

- **To start validating you must call following methods**
```     
    // "this" reference to the current class in which these annotations are used in.
    FieldValidator.validateWithCurrentClass( this, new ValidationListener() {
            @Override
            public void validateSuccess() {
                // if all validations go well
            }

            @Override
            public void validateFailed(@NonNull ValidationError validationError, @NonNull FilterChain filterChain) {
                
                validationError.getMessage(); // the message you provided
                validationError.getView(); // on which view the validation failed
                
                // if you want to stop validation process here you need nothing to do and show errors etc in your flow.
                
                // if you want to show errors, but also continue validating other views
                // in cases where you want to validate all views and show errors in all of them in the end
                
                // call this to continue validating next view, this sequence was specified by @Order(1), @Order(2)...
                filterChain.doFiter(); // continue validation
                
           
                
            }
        }
    
    );
``` 

Other annotaions available:
- `@LengthRange` : defines length
- `@ConfirmPassword` : Compares the text in this EditText with specified EditText to confirm password entry.



## Contributing

Contributions are welcomed as long as they dont break the code. Please create an issue and have a discussion before pull request.

## Hosting

Hosted at JCenter: https://bintray.com/syedowaisali/maven/mint-validator

## Authors

* **Owais Ali** - *Initial work* - [@syedowaisali](https://github.com/syedowaisali)

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](https://github.com/syedowaisali/mint-validator/blob/master/LICENSE) file for details.

*Sources from Android and Android APIs are subject to the licensing terms as per Android Open Source Project (AOSP).*

