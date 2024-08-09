
export function saveItem(item) {
    
     //Create JSON
     const itemJSON = JSON.stringify(item);
        
     //------
     const http = new XMLHttpRequest(); 
     http.onreadystatechange = () =>{  // callback function  // 
       if(http.readyState == 4){
         if(http.status == 200){
   
           var responseTextJSON = JSON.stringify(http.responseText); // stringyfy() ---->  method eka karanne object ekak Json Object ekakata convert karanawa // 
           console.log(responseTextJSON);
           
 
         }else{
         
           console.error("Failed");
           console.error("Status",http.status);
           console.error("Ready State",http.readyState);
           alert("Item Saved Sucessfully ! !");
           loadTable(item);
           refresh();
           
         }
 
       }else{ console.error("Ready State",http.readyState); }
 
     }
     http.open("POST","http://localhost:8080/POSSystem/item",true);  // <-- mrtana true karoth refresh wenne natuwa awlk natuwa yanawa //  
     http.setRequestHeader("Content-Type","application/json");
     http.send(itemJSON);


}


function updateRow(item,rowNumber) {
    // Select the  row 
    let table = document.getElementById("itemTable1");
    let row = table.rows[rowNumber+1] 
    
    // Update the cells in the selected row
    row.cells[0].innerHTML = item.code;
    row.cells[1].innerHTML = item.name;
    row.cells[2].innerHTML = item.qty;
    row.cells[3].innerHTML = item.price;

}
function deletRow(rowNumber) {
    // Select the second row (index 1 because it's 0-based)
    let table = document.getElementById("itemTable1");
    table.deleteRow(rowNumber+1); 
}

function loadTable(item){
    
    $('#ItemManage .tableRow').append(
        '<tr> ' +
            '<td>' + item.code + '</td>' +
            '<td>' + item.name + '</td>' +
            '<td>' + item.qty + '</td>' +
            '<td>' + item.price + '</td>' +
        '</tr>' 
    );
    
}

export function updateItem(item,rowNumber){
    
    //Create JSON
    const itemJSON = JSON.stringify(item);
    
    //------
    const http = new XMLHttpRequest(); 
    http.onreadystatechange = () =>{  // callback function  // 
      if(http.readyState == 4){
        if(http.status == 200){
  
          var responseTextJSON = JSON.stringify(http.responseText); 
          console.log(responseTextJSON);
          
        }else{
          
          console.error("Failed");
          console.error("Status",http.status);
          console.error("Ready State",http.readyState);
          
          alert("Item Details Updated Sucessfully ! !");
          updateRow(item,rowNumber);  
          refresh();
          
        }

      }else{ console.error("Ready State",http.readyState); }

    }
    http.open("PUT","http://localhost:8080/POSSystem/item",true); 
    http.setRequestHeader("Content-Type","application/json");
    http.send(itemJSON);

}



export function deleteItem(item,rowNumber){
    const itemJSON = JSON.stringify(item);
        
    //------
    const http = new XMLHttpRequest(); 
    http.onreadystatechange = () =>{  // callback function  // 
      if(http.readyState == 4){
        if(http.status == 200){
  
            var responseTextJSON = JSON.stringify(http.responseText); 
          console.log(responseTextJSON);
          
          
        }else{
            
          console.error("Failed");
          console.error("Status",http.status);
          console.error("Ready State",http.readyState);
          deletRow(rowNumber);
          alert("Item Deleted !!");
          refresh();
          
        }

      }else{ console.error("Ready State",http.readyState); }

    }
    
    http.open("DELETE","http://localhost:8080/POSSystem/item",true); 
    http.setRequestHeader("Content-Type","application/json");
    http.send(itemJSON);
}


function refresh(){
    $('#ItemManage .itemId').val('');
    $('#ItemManage .itemName').val('');
    $('#ItemManage .itemQty').val('');
    $('#ItemManage .itemPrice').val('');
}

export function getAllItems() {
    const http = new XMLHttpRequest(); 
  http.onreadystatechange = () =>{  // callback function  // 
    if(http.readyState == 4){
      if(http.status == 200){
        
        //   var responseTextJSON = JSON.stringify(http.responseText); 
        const items = JSON.parse(http.responseText);
        // $('#CustomerManage .tableRow').empty();
        items.forEach(item => {

            $('#ItemManage .tableRow').append(
              '<tr> ' +
                  '<td>' + item.code + '</td>' +
                  '<td>' + item.name + '</td>' +
                  '<td>' + item.qty + '</td>' +
                  '<td>' + item.price + '</td>' +
              '</tr>' 
            );
        });
        

      }else{
      
        console.error("Failed");
        console.error("Status",http.status);
        console.error("Ready State",http.readyState);
        refresh();

      }

    }else{ console.error("Ready State",http.readyState); }

  }
  http.open("GET","http://localhost:8080/POSSystem/item",true); 
  http.setRequestHeader("Content-Type","application/json");
  http.send();
}
