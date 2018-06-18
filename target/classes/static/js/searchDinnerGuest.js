var allergyArray = [];
var intoleranceArray = [];
var foodAversionArray = [];
var allergyNameArray = [];
var intoleranceNameArray = [];
var foodAversionNameArray = [];
var id = 0;
$(document).ready(function () {
    $("#firstName-text").keypress(function(e){
        if(e.which === 13) {
            searchGuests();
        }
     });
    $("#lastName-text").keypress(function(e){
        if(e.which === 13) {
            searchGuests();
        }
    });
    $('#searchGuest').on('click', function(){
        searchGuests();
    });


});

function deleteGuest(dgId , firstName, lastName) {
    if (confirm("Are you sure you want to delete " + firstName + " " + lastName + " from the database?")) {
        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/dg/foodforall/delete/' + dgId,
            success: function() {
                alert("Dinner Guest has been removed.");
                $('#' +dgId).empty();

                
            }
                    
        });
    }
}

function loadRows(type) {
    clearRows(type);
    var localType;
    var outType;
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
       
            var name = type.name.replace(/\s+/g, '-').toLowerCase();
            var checkVar = 0;
            if (outType === "allergy" && allergyNameArray.indexOf(name) < 0) {
               
                 row = '<button type="button" class="list-group-item list-group-item-action" id="'+ outType + name +
                        '">' + name + '</button>';
                 checkVar++;
             } else if (outType === "intolerance" && intoleranceNameArray.indexOf(name) < 0) {
                
                 row = '<button type="button" class="list-group-item list-group-item-action" id="'+ outType + name +
                        '">' + name + '</button>';
                 checkVar++;
             } else  if (outType === "foodAversion" && foodAversionNameArray.indexOf(name) < 0){
         
                 row = '<button type="button" class="list-group-item list-group-item-action" id="'+ outType + name +
                        '">' + name + '</button>';
                 checkVar++;
             }

             var obj = type;    
                if(checkVar > 0) {
                contentRows.append(row);
            }
                clickManager(obj, outType, name);
    
            });
           
        },
        error: function() {
            console.log('woops');
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
                allergyNameArray.splice(index,1);
                $(this).css('background-color', '#ffffff');
            } else {
                allergyArray.push(obj);
                allergyNameArray.push(obj.name);
                $(this).css('background-color', '#36c423');
        }
        console.log(allergyArray);
    }
         if(type === 'intolerance') {
            index = intoleranceArray.indexOf(obj);
            if(index > -1) {
                intoleranceArray.splice(index, 1);
                intoleranceNameArray.splice(index,1);
                $(this).css('background-color', '#ffffff');
            } else {
                intoleranceArray.push(obj);
                intoleranceNameArray.push(obj.name);
                $(this).css('background-color', '#36c423');
        }
        console.log(intoleranceArray);
         }
          if(type === 'foodAversion') {
            index =foodAversionArray.indexOf(obj);
            if(index > -1) {
                foodAversionArray.splice(index, 1);
                foodAversionNameArray.splice(index,1);
                $(this).css('background-color', '#ffffff');
            } else {
                foodAversionArray.push(obj);
                foodAversionNameArray.push(obj.name);
                $(this).css('background-color', '#36c423');
        }
        console.log(foodAversionArray);
         }
     });
}
function clearRows(type) {
    $('#' + type + 'List').empty();
}

function editGuest(dgId) {
    allergyArray = [];
    intoleranceArray = [];
    foodAversionArray = [];
    allergyNameArray = [];
    intoleranceNameArray = [];
    foodAversionNameArray = [];
    clearRows('alreadyAllergy');
    clearRows('alreadyIntolerance');
    clearRows('alreadyFoodAversion');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dg/foodforall/edit/' + dgId,
        success: function(data, status) {
            id = data.dinnerGuestId;
            $('#firstName-edit-text').val(data.firstName);
            $('#lastName-edit-text').val(data.lastName);
            $('#eMail-edit-text').val(data.emailAddress);
            $('#phone-edit-text').val(data.phone);
            clearRows('allergy');
            clearRows('intolerance');
            clearRows('foodAversion');
        $.each(data.allergies, function(index, allergy){
            var allergyName = '';
            var allergyId = 0;
            var deleteId = '';
            var divId = "";
            allergyName = allergy.name.replace(/\s+/g, '-').toLowerCase();
            allergyId = allergy.allergyId;
            divId = allergyName + allergyId;
            deleteId = divId + 'Delete';
            $('#alreadyAllergyList').append('<div id="' + divId + 'Id"><li class="list-group-item" id="id">'+ allergy.name +
                '<button class="btn btn-sm btn-info" id="' + deleteId +'" style="position:' +
                'absolute;right: 10px;top: 5px;background-color: #000000;"><span class="glyphicon glyphicon-trash">' +
                '</span></button></li></div>');            
                var obj = allergy;
                clickFunction(obj, allergyArray, allergyNameArray, divId, deleteId);
                allergyArray.push(obj);
                allergyNameArray.push(obj.name);
                console.log(obj);
            });
            $.each(data.intolerances, function(index, intolerance){
                var intoleranceName = '';
                var intoleranceId = 0;
                var divId = '';
                var deleteId = '';
                intoleranceName = intolerance.name.replace(/\s+/g, '-').toLowerCase();
                intoleranceId = intolerance.intoleranceId;
                divId = intoleranceName + intoleranceId;
                deleteId = divId + 'Delete';
                console.log(divId);
                $('#alreadyIntoleranceList').append('<div id="' + divId + 'Id"><li class="list-group-item" id="id">'+ intolerance.name +
                    '<button class="btn btn-sm btn-info" id="' + deleteId +'" style="position:' +
                    'absolute;right: 10px;top: 5px;background-color: #000000;"><span class="glyphicon glyphicon-trash">' +
                    '</span></button></li></div>');       
                    var obj = intolerance;
                    clickFunction(obj, intoleranceArray, intoleranceNameArray, divId, deleteId);
                    intoleranceArray.push(obj);
                    intoleranceNameArray.push(obj.name);
                    console.log(obj);
                });
            $.each(data.foodAversions, function(index, foodAversion){
                var foodAversionName = '';
                var foodAversionId = 0;
                var divId = '';
                var deleteId = '';
                foodAversionName = foodAversion.name.replace(/\s+/g, '-').toLowerCase();
                foodAversionId = foodAversion.foodAversionId;
                divId = foodAversionName + foodAversionId;
                deleteId = divId + 'Delete';
                
                $('#alreadyFoodAversionList').append('<div id="' + divId + 'Id"><li class="list-group-item" id="id">'+ foodAversion.name +
                    '<button class="btn btn-sm btn-info" id="' + deleteId +'" style="position:' +
                    'absolute;right: 10px;top: 5px;background-color: #000000;"><span class="glyphicon glyphicon-trash">' +
                    '</span></button></li></div>');       
                
                var obj = foodAversion;
                clickFunction(obj, foodAversionArray, foodAversionNameArray, divId, deleteId);
                foodAversionArray.push(obj);
                foodAversionNameArray.push(obj.name);
                console.log(obj);
            });
            loadRows('allergy');
            loadRows('intolerance');
            loadRows('foodAversion');
            
        },
        error: function() {
            
        }
    });
}
function  clickFunction(obj, objArray, nameArray, divId, deleteId) {
            $('#' + deleteId).on('click', function(){
                console.log('clicked');
                deleteFromArray(obj, objArray, nameArray, divId);
        });
}
function deleteFromArray(obj, objArray, nameArray, divId) {
        if (confirm("Are you sure you want to delete " + obj.name + " from this guest?")) {
            var index = objArray.indexOf(obj);
            if(index > -1) {
                objArray.splice(index, 1);
            }
            $('#' + divId + 'Id').remove();
            loadRows('allergy');
            loadRows('intolerance');
            loadRows('foodAversion');
        }
    }
    
    $('#clearSearch').on('click', function(){
        clearScreen();
    });
    
    $( '#submitNewGuest').on('click', function(){

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

});

$('#updateGuestButton').on('click', function(){
    updateGuest();
});

function updateGuest() {
    var firstName = '';
    var lastName = '';
    firstName = $('#firstName-edit-text').val();
    lastName = $('#lastName-edit-text').val();
    console.log(firstName);
    if (confirm('Are you sure you want to update ' + firstName + ' ' + lastName + ' from the database?')) {
        $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/dg/foodforall/updateGuest',
        data: JSON.stringify({
            dinnerGuestId: id,
            firstName: firstName,
            lastName: lastName,
            emailAddress: $('#eMail-edit-text').val(),
            phone: $('#phone-edit-text').val(),
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
              alert('Dinner Guest ' + firstName + ' ' + lastName + ' has been updated in the system');
              clearScreen();
               

          },
         error: function() {
                console.log('woops');
         }
    });
        
    }

}
function clearScreen() {
    allergyArray = [];
    intoleranceArray  = [];
    foodAversionArray  = [];
    allergyNameArray = [];
    intoleranceNameArray = [];
    foodAversionNameArray = [];
    id = 0;
    $('#searchResults').empty();
    $('#firstName-text').val('');
    $('#lastName-text').val('');
    $('#editFormDiv').hide();
}

    function searchGuests() {
        var contentRow = $('#searchResults');
        contentRow.empty(); 
        var firstNameJs =  $('#firstName-text').val();
        var lastNameJs = $('#lastName-text').val();
        
       
        var data = {
            firstName: firstNameJs,
            lastName: lastNameJs
         };
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/dg/foodforall/searchGuest',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function(resultList){
                
               
               $.each(resultList, function(index, result){
                   var dgId = result.dinnerGuestId;
                   var row='';
                   row = '<div id="'+ dgId + '"><button type="button" class="btn btn-lg btn-info collapsed" id="' + dgId + 'button"' +
                           'data-toggle="collapse" data-target="#' + dgId + 'info">' + 
                           result.firstName +' '+ result.lastName + '</button>';
                    
                   console.log(result.firstName);
                   row += '<div class="collapse" id="'+ dgId +'info" style="font-size:20px;"><b>Phone:</b> ' + result.phone + '<br>' +
                           '<b>E-Mail:</b> ' + result.emailAddress + '<br><b>Allergies:</b> ';
                   $.each(result.allergies, function(index, allergy){
                       row += allergy.name + '  ';
        
                   });
                   row += '<br><b>Intolerances:</b> ';
                   $.each(result.intolerances, function(index, intolerance){
                       row +=  intolerance.name + '  ';

                   });
                   row += '<br><b>Food Aversions:</b> ';
                   $.each(result.foodAversions, function(index, foodAversion){
                       row += foodAversion.name + '  ';

                   });
                   row+= '</div><button class="btn btn-lg btn-info" id="edit'+ dgId + '">' +
                           '<span class="glyphicon glyphicon-edit"></span></button><button class="btn btn-lg btn-info" ' +
                           'id="delete'+ dgId + '"><span class="glyphicon glyphicon-trash"></span></button><br><hr /></div>';
                   contentRow.append(row);
                    $('#edit' + dgId).click(function(event){ 
                        $('#editFormDiv').toggle();
                        editGuest(dgId);
                    });
                    $('#delete' + dgId).click(function(event){ 
                        deleteGuest(dgId, result.firstName, result.lastName);
                    });
                   
               });
            },
            error: function(){
                console.log("something ain't right");
            }
        });
        
    }
 