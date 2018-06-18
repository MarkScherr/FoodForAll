var appId = "5791b250";
var appKey = "114de019503c6203901cd6b432c5bd8a";
var apiIngredientArray = [];
var ingredientArray = [];

$('#ingredientAdd-text').keypress(function(e){
    if(e.which === 13) {
      addIngredient();
    }
  });
$('#ingredientAdd-button').on('click', function(){
    addIngredient();
});

$("#recipeSearch-text").keypress(function(e){
    if(e.which === 13) {
        $('#searchFindings').empty();
        searchRecipe();
    }
});
$('#searchForRecipe').on('click', function(){
    $('#searchFindings').empty();
    searchRecipe();
});

$('#validateInputIngredients').on('click', function(){
    $('#dinnerGuestsWithIssues').show();
    validateIngredients(ingredientArray);
});
    
function validateIngredients(arrayToValidate) {
    var resultRow = $('#dinnerGuestsWithIssues');
    resultRow.empty();
    var row='';
    var checkBit = 0;
    var data = {
        apiIngredients: arrayToValidate
    };
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/verify/verifyAPI',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
        
        success: function(data) {
           apiIngredientArray =[];
           checkBit = 0;
            row = '<div class="col-md-5" id="issueDiv"><h3 class="side-title">Guests Unhappy</h3><ul class="list-group">';
            $.each(data, function(index, dg){
            if(index === 0) {
                resultRow.append('<img src="potatoGrumpy.png" alt="Issues" id="potatoGrumpyImage">');
                checkBit = 1;
            }
            var phone = dg.phone;
            row += '<a class="tooltips"><li class="list-group-item list-group-item-warning">'+ 
                    dg.firstName +' '+ dg.lastName + '</li><span>'+ dg.conflictWithRecipe + '</span></a>';

           });
            
           
           if(checkBit === 0) {
               $('#issueDiv').empty();
                resultRow.empty();
                resultRow.append('<img src="potatoHappy.png" alt="No Issues" id="potatoHappyImage">');
           } else {
               row+='</ul></div>';
                resultRow.append(row);
             
           }
           row = '';
        }, 
        error: function() {
            console.log("There was an error in connectivity");
        }
        
        
        
    });
    
}

function addIngredient() {
        var ingredientText = $('#ingredientAdd-text').val();
        if(ingredientText !== '' && ingredientArray.indexOf(ingredientText) < 0) {  
        
        ingredientArray.push(ingredientText);
        validateIngredients(ingredientArray);
        if  (ingredientArray.length === 1) { 
            $('#validateInputIngredients').show();   
        }
        ingredientTextNoSpaces = ingredientText.replace(/\s+/g, '-').toLowerCase();

        $('#ingredientList').append('<div id="' + ingredientTextNoSpaces + 'id"><li class="list-group-item" id="id">'+ ingredientText +
            '<button class="btn btn-sm btn-info" id="' + ingredientTextNoSpaces +'" style="position:' +
            'absolute;right: 10px;top: 5px;background-color: #000000;"><span class="glyphicon glyphicon-trash">' +
            '</span></button></li></div>');
            
            console.log(ingredientArray);
           
            clickFunction(ingredientTextNoSpaces, ingredientText);
            
     
            $('#ingredientAdd-text').val('');    

    } else {
         $('#ingredientAdd-text').val('');  
    }
    
}
function  clickFunction(ingredientTextNoSpaces, ingredientText) {
            $('#' + ingredientTextNoSpaces).on('click', function(){
            $('#' + ingredientTextNoSpaces + 'id').remove();
            deleteFromArray(ingredientText);
        });
}
function deleteFromArray(ingredient) {
            var index = ingredientArray.indexOf(ingredient);
            if(index > -1) {
                ingredientArray.splice(index, 1);
                validateIngredients(ingredientArray);
                if(ingredientArray.length === 0) {
                     $('#validateInputIngredients').hide();
                     $('#dinnerGuestsWithIssues').hide();
                }
                
            }
          
    }
    
function searchRecipe() {
    var searchItem   = $('#recipeSearch-text').val();
    if (searchItem === '') {
        alert('Search did not contain any value.');
    } else {
    $('#apiDiv #searchFindings').empty();
    var row;
    var recipeId;
    var recipeIngredientArray = [];
    $.ajax({
        type: 'GET',
        url: 'https://api.edamam.com/search?q=' + searchItem + '&app_id=' + appId + '&app_key=' + appKey + '&from=0&to=15',
        success: function(data, status) {
            row = '<div id="searchFindings">';
            $('#apiDiv').append(row);
            $.each(data.hits, function(index, thisHit){
                var nameId = thisHit.recipe.label;
                row = '<a href="'+ thisHit.recipe.url +'"><img src="' + thisHit.recipe.image +'" alt="image did not load"></img></a>' +
                        '<h4>' + thisHit.recipe.label + '</h4><button type="button" class="btn btn-success"' + 
                        ' id="recipeButton' + index + '" style="background-color:#36c423;">Validate</button><hr />'; 
                recipeId = 'recipeButton' + index;
                $('#apiDiv').append(row);
                recipeIngredientArray = thisHit.recipe.ingredientLines;
                apiClickHandler(recipeId, recipeIngredientArray);
            });
           $('#apiDiv').append('</div>');
        },
        error: function() {
            console.log("Troubles connecting with the API");
        }
    });
    
    $('#recipeSearch-text').val('');
    }
}

function  apiClickHandler(recipeId , recipeIngredientArray) {
        apiIngredientArray =[];
        $('#' + recipeId).on('click', function() {
        console.log("this button clicked");
        for(var i = 0 ; i < recipeIngredientArray.length; i++) {
            apiIngredientArray.push(recipeIngredientArray[i]);
        }
        validateIngredients(apiIngredientArray);
        });
}
