// registration form validations
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

    } else if ((password.value.length < 1) || (password.value.length > 15)) {
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
        console.log("Password validated");
        return true;
    }
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
            console.log("User type validated");
            return true;
        }
    }
    alert('Select user-type');
    return false;
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

// log in validations
function loginValidateEmail(email) {
    if(email.value == ""){
        email.style.background = 'Yellow';
        alert('Email field is empty');
        return false;
    }
    else{
        email.style.background = 'White';
        return true;
    }
}

function loginValidatePassword(password) {
    if (password.value == "") {
        password.style.background = 'Yellow';
        alert('Password is empty');
        return false;
    }
    else{
        password.style.background = 'White';
        return true;
    }
}

function loginValidation() {
    console.log("Inside loginValidation");
    var username = document.login.username;
    var password = document.login.password;

    if(loginValidateEmail(username)){
        if(loginValidatePassword(password)){
            return true;
        }
    }
    return false;
}

// drop down validation
function checkSelect(select){
    if(select.selectedIndex == 0){
         alert('Select option from drop down');
         return false;
    }
    console.log("product selection validated");
    return true;
}

function assignUsersValidation(){
     console.log("Inside assignUsersValidation");
     var selected_consultant = document.assignUsers.selected_consultant;
     var selected_customer = document.assignUsers.selected_customer;
     if(checkSelect(selected_consultant) && checkSelect(selected_customer)){
        return true;
     }
     return false;
}

function buyProductValidation() {
     console.log("Inside buyProductValidation");
     var selected_product = document.buyProducts.selected_product;
     if(checkSelect(selected_product)){
        return true;
     }
     return false;
}

// add product validation
function addProductValidation(){
    console.log("Inside addProductValidation");
    var productName = document.addProduct.product_name;
    if(validateNames(productName)){
        return true;
    }
    return false;
}

// search Users
function searchUsersValidation() {
    console.log("Inside searchUsersValidation");
     var first_name = document.searchUsers.first_name;
     var last_name = document.searchUsers.last_name;
     var age = document.searchUsers.age;
     var address = document.searchUsers.address;
     var email = document.searchUsers.email;
     var sex = document.searchUsers.sex;
     var user_type = document.searchUsers.user_type;

     if(first_name.value=="" && last_name.value=="" &&
        age.value=="" && address.value=="" &&
        email.value=="" && sex.value=="" && user_type.value==""){
            alert('Please, select at least one search criteria');
            return false;
        }
     else{
        return true;
     }
}
