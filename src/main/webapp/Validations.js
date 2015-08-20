function validateNames(names) {
 var letters = /^[a-zA-Z]+$/;
     if(names.value == ""){
         names.style.background = 'Yellow';
         alert('Name field is empty');
         return false;
     }
     else if(!letters.test(names.value)){
         names.style.background = 'Yellow';
         alert('Name must have alphabetic characters only');
         return false;
     }
     else{
         names.style.background = 'White';

         console.log("Name " + names.value + " validated");
         return true;
     }
 }

function validateAddress(address) {
     var letters = /^[0-9a-zA-Z]+$/;
     if(address.value == ""){
         address.style.background = 'Yellow';
         alert('Address field is empty');
         return false;
     }
     else if(!letters.test(address.value)){
         address.style.background = 'Yellow';
         alert('Address must have alphanumeric characters only');
         return false;
     }
     else{
         address.style.background = 'White';
         return true;
         console.log("Address validated");
     }
 }

function validateEmail(email) {
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if(email.value == ""){
        email.style.background = 'Yellow';
        alert('Email field is empty');
        return false;
    }
    else if(!filter.test(email.value)) {
        email.style.background = 'Yellow';
        alert('Please provide a valid email address');
        return false;
    }
    else{
        email.style.background = 'White';
        console.log("email validated");
        return true;
    }
}

function validateAge(age){
    var numbers = /^[0-9]+$/;
     if(age.value == ""){
        age.style.background = 'Yellow';
        alert('Age field is empty');
        return false;
     }
     else if(!numbers.test(age.value)){
        age.style.background = 'Yellow';
        alert('Age filed must contain numeric characters only');
        return false;
     }
     else{
        age.style.background = 'White';
        console.log("Age validated");
        return true;
     }
}


function validatePassword(password) {
    var illegalChars = /[\W_]/; // allow only letters and numbers

    if (password.value == "") {
        password.style.background = 'Yellow';
        alert('Password is empty');
        return false;

    } else if ((password.value.length < 7) || (password.value.length > 15)) {
        password.style.background = 'Yellow';
        alert('The password is the wrong length.');
        return false;

    } else if (illegalChars.test(password.value)) {
        password.style.background = 'Yellow';
        alert('The password contains illegal characters.');
        return false;

    } else if ( (password.value.search(/[a-zA-Z]+/)==-1) || (password.value.search(/[0-9]+/)==-1) ) {
        password.style.background = 'Yellow';
        alert('The password must contain at least one numeral.');
        return false;

    } else {
        password.style.background = 'White';
    }

    console.log("Password validated");
    return true;
}

function validateGender(sex){
    for (var i=0; i < sex.length; i++) {
        if (sex[i].checked) {
            console.log("Gender validated");
            return true;
        }
    }
    alert('Select gender');
    return false;

}

function validateUserType(type){
    for (var i=0; i < type.length; i++) {
        if (type[i].checked) {
            console.log("Usertype validated");
            return true;
        }
    }
    alert('Select user-type');
    return false;
}

function checkValidationAsset(){
    form = document.registration;
    console.log("Java script file is loaded");
    console.log(form.nodeName);
}

function checkForm(){
console.log(formValidation());
}

function formValidation() {
    console.log("Inside formValidation");
    var first_name = document.registration.first_name;
    var last_name = document.registration.last_name;
    var age = document.registration.age;
    var address = document.registration.address;
    var email = document.registration.email;
    var password = document.registration.password;
    var sex = document.registration.sex;
    var user_type = document.registration.user_type;

    if(validateNames(first_name)){
         if(validateNames(last_name)){
            if(validateAge(age)){
                if(validateAddress(address)){
                    if(validateEmail(email)){
                        if(validatePassword(password)){
                            if(validateGender(sex)){
                                if(validateUserType(user_type)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
         }
    }
    return false;
}