var allergyArray = [];
var intoleranceArray  = [];
var foodAversionArray  = [];
var allergyArrayChecker = [];
var intoleranceArrayChecker = [];
var foodAversionArrayChecker = [];


$(document).ready(function () {
loadRows('allergy');
loadRows('intolerance');
loadRows('foodAversion');

$('#clearNewGuest').on('click', function(){
    loadRows('allergy');
    loadRows('intolerance');
    loadRows('foodAversion');
    clearGuestList();
});

$('#allergyButton').on('click', function(){
            allergyName = $('#allergyInput').val();
            if(allergyName === '' || allergyArrayChecker.indexOf(allergyName) >= 0) {
             alert('The value you entered is invalid');
             $('#allergyInput').val('');
            }   else { 
         addAllergy();
    }
});

$("#allergyInput").keypress(function(e){
     if(e.which === 13) {
       allergyName = $('#allergyInput').val();
        if(allergyName === '' || allergyArrayChecker.indexOf(allergyName) >= 0) {
             alert('The value you entered is invalid');
             $('#allergyInput').val('');
        }   else { 
         addAllergy();
        }
    }
});
     
$('#intoleranceButton').on('click', function(){
        intoleranceName = $('#intoleranceInput').val();
        if(intoleranceName === '' || intoleranceArrayChecker.indexOf(intoleranceName) >= 0) {
             alert('The value you entered is invalid');
             $('#intoleranceInput').val('');
        }   else { 
     addIntolerance();
    }
});
$("#intoleranceInput").keypress(function(e){
    if(e.which === 13) {
            intoleranceName = $('#intoleranceInput').val();
                if(intoleranceName === '' || intoleranceArrayChecker.indexOf(intoleranceName) >= 0) {
             alert('The value you entered is invalid');
             $('#intoleranceInput').val('');
                }   else { 
             addIntolerance();
            }
    }
 });
     
$('#foodAversionButton').on('click', function(){
        foodAversionName = $('#foodAversionInput').val();
            if(foodAversionName === '' || foodAversionArrayChecker.indexOf(foodAversionName) >= 0) {
             alert('The value you entered is invalid');
             $('#foodAversionInput').val('');
            }   else { 
         addFoodAversion();
        }
});

$("#foodAversionInput").keypress(function(e){
    if(e.which === 13) {
            foodAversionName = $('#foodAversionInput').val();
             if(foodAversionName === '' || foodAversionArrayChecker.indexOf(foodAversionName) >= 0) {
             alert('The value you entered is invalid');
             $('#foodAversionInput').val('');
                }   else { 
             addFoodAversion();
            }
    }
 });

$( '#submitNewGuest').on('click', function(){
    var firstName =$('#firstName-text').val();
    var lastName = $('#lastName-text').val();
    if(lastName === '' || firstName === '') {
        alert("First and last name are required fields");
    } else {
    $.ajax({
    type: 'POST',
    url: 'http://localhost:8080/foodforall/submitGuest',
    data: JSON.stringify({
        firstName: $('#firstName-text').val(),
        lastName: $('#lastName-text').val(),
        emailAddress: $('#eMail-text').val(),
        phone: $('#phone-text').val(),
        allergies:  allergyArray,
        intolerances: intoleranceArray,
        foodAversions: foodAversionArray        
    }),
          headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      'dataType': 'json',
      success: function() {
           allergyArray = [];
           intoleranceArray  = [];
           foodAversionArray  = [];
    
                loadRows('allergy');
                loadRows('intolerance');
                loadRows('foodAversion');
                clearGuestList();
      },
     error: function() {
            console.log('woops');
     }
});
    }
});

});


function loadRows(type) {
    clearRows(type);
    var localType;
    var outType;
    allergyArrayChecker = [];
    intoleranceArrayChecker = [];
    foodAversionArrayChecker = [];

    
    if (type === 'allergy') {
        localType = 'allergies';
        outType = type;
    } else if (type === 'intolerance') {
        localType = type + 's';
        outType = type;
    } else if (type === 'foodAversion') {
        localType = type + 's';
        outType = type;
    }
    var contentRows = $('#' + type + 'List');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/foodforall/' + localType,
        success: function(resultList){
            var row = '';
            $.each(resultList, function(index, type){
            var id = 0;
            if (outType === "allergy") {
                 var id = type.allergyId;
                 name = type.name;
                 allergyArray=[];
                 allergyArrayChecker.push(name);
             } else if (outType === "intolerance") {
                 name= type.name;
                 var id = type.intoleranceId;        
                 intoleranceArray = [];
                 intoleranceArrayChecker.push(name);
             } else  if (outType === "foodAversion"){
                 name= type.name;
                 var id = type.foodAversionId;
                 foodAversionArray = [];
                 foodAversionArrayChecker.push(name);
             }
             var nameWithoutSpaces = type.name.replace(/\s+/g, '-').toLowerCase();
             console.log(nameWithoutSpaces);
             var obj = type;
                row = '<button type="button" class="list-group-item list-group-item-action" id="'+outType + nameWithoutSpaces +'">' + name + '</button>';
                    contentRows.append(row);
                    clickManager(obj, outType, nameWithoutSpaces);
            });
             contentRows.append( '</select>');
        },
        error: function() {
            console.log('woops');
        }
    });
}

function clearRows(type) {
    $('#' + type + 'List').empty();
}

function clearGuestList() {
    $("#firstName-text").val('');
    $("#lastName-text").val('');
    $("#eMail-text").val('');
    $("#phone-text").val('');
}

function addAllergy() {
        
        $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/foodforall/allergy',
        data: JSON.stringify({
          name: $('#allergyInput').val()
        }),
        headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function() {
          $('#allergyInput').val('');
          loadRows('allergy');
        },
        error: function() {
          console.log('try again');
        }
      });
    
}

function addIntolerance() {
      $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/foodforall/intolerance',
        data: JSON.stringify({
          name: $('#intoleranceInput').val()
        }),
        headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function() {
          $('#intoleranceInput').val('');
          loadRows('intolerance');
        },
        error: function() {
          console.log('try again');
        }
      });
}

function addFoodAversion() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/foodforall/foodAversion',
            data: JSON.stringify({
              name: $('#foodAversionInput').val()
            }),
            headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function() {
              $('#foodAversionInput').val('');
              loadRows('foodAversion');
            },
            error: function() {
              console.log('try again');
            }
      });
}

function clickManager(obj, type, name) {
        var index = -1;
        $('#' + type + name).click(function(event){
        if(type === 'allergy') {
            index = allergyArray.indexOf(obj);
            if(index > -1) {
                allergyArray.splice(index, 1);
                $(this).css('background-color', '#ffffff');
            } else {
                allergyArray.push(obj);
                $(this).css('background-color', '#36c423');
        }
        console.log(allergyArray);
    }
         if(type === 'intolerance') {
            index = intoleranceArray.indexOf(obj);
            if(index > -1) {
                intoleranceArray.splice(index, 1);
                $(this).css('background-color', '#ffffff');
            } else {
                intoleranceArray.push(obj);
                $(this).css('background-color', '#36c423');
        }
        console.log(intoleranceArray);
         }
          if(type === 'foodAversion') {
            index =foodAversionArray.indexOf(obj);
            if(index > -1) {
                foodAversionArray.splice(index, 1);
                $(this).css('background-color', '#ffffff');
            } else {
                foodAversionArray.push(obj);
                $(this).css('background-color', '#36c423');
        }
        console.log(foodAversionArray);
         }
     });
}
