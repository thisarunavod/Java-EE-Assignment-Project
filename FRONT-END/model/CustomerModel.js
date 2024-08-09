

export function saveCustomer(customer) {
    
    //Create JSON
    const customerJASON = JSON.stringify(customer);
        
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
          alert("Customer Saved Sucessfully ! !");
          loadTable(customer);
          refresh();
          
        }

      }else{ console.error("Ready State",http.readyState); }

    }
    http.open("POST","http://localhost:8080/POSSystem/customer",true);  // <-- mrtana true karoth refresh wenne natuwa awlk natuwa yanawa //  
    http.setRequestHeader("Content-Type","application/json");
    http.send(customerJASON);


}

function loadTable(customer){
  $('#CustomerManage .tableRow').append(
      '<tr> ' +
          '<td>' + customer.id + '</td>' +
          '<td>' + customer.name + '</td>' +
          '<td>' + customer.address + '</td>' +
          '<td>' + customer.salary + '</td>' +
      '</tr>' 
  );
}


function updateRow(customer,rowNumber) {
  // Select the  row 
  let table = document.getElementById("customerTable1");
  let row = table.rows[rowNumber+1] 
  
  // Update the cells in the selected row
  row.cells[0].innerHTML = customer.id;
  row.cells[1].innerHTML = customer.name;
  row.cells[2].innerHTML = customer.address;
  row.cells[3].innerHTML = customer.salary;

}
function deletRow(rowNumber) {
  
  let table = document.getElementById("customerTable1");
  table.deleteRow(rowNumber+1); 
}



function refresh(){
  $('#CustomerManage .custId').val('');
  $('#CustomerManage .custName').val('');
  $('#CustomerManage .custAddress').val('');
  $('#CustomerManage .custSalary').val('');
  // $('#CustomerManage .invalidCustId').text('');
  // $('#CustomerManage .invalidCustName').text('');
  // $('#CustomerManage .invalidCustAddress').text('');

  // reloadTable();
}






export function getAllCustomers() {
  const http = new XMLHttpRequest(); 
  http.onreadystatechange = () =>{  // callback function  // 
    if(http.readyState == 4){
      if(http.status == 200){
        
        //   var responseTextJSON = JSON.stringify(http.responseText); 
        const customers = JSON.parse(http.responseText);
        $('#CustomerManage .tableRow').empty();
        customers.forEach(customer => {

          // $('#CustomerManage .tableRow').empty();
            $('#CustomerManage .tableRow').append(
              '<tr> ' +
                  '<td>' + customer.id + '</td>' +
                  '<td>' + customer.name + '</td>' +
                  '<td>' + customer.address + '</td>' +
                  '<td>' + customer.salary + '</td>' +
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
  http.open("GET","http://localhost:8080/POSSystem/customer",true); 
  http.setRequestHeader("Content-Type","application/json");
  http.send();
}

export function updateCustomer(customer,rowNumber){
    //Create JSON
    const customerJASON = JSON.stringify(customer);
        
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
          
          alert("Customer Details Updated Sucessfully ! !");
          // location.reload();
          updateRow(customer,rowNumber);
          refresh();
          
        }

      }else{ console.error("Ready State",http.readyState); }

    }
    http.open("PUT","http://localhost:8080/POSSystem/customer",true); 
    http.setRequestHeader("Content-Type","application/json");
    http.send(customerJASON);
}

export function deleteCustomer(customer,rowNumber){
    //Create JSON
    const customerJASON = JSON.stringify(customer);
        
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
          
          alert("Customer Deleted !!");
          location.reload();
          // deletRow(rowNumber)
          refresh();
          
        }

      }else{ console.error("Ready State",http.readyState); }

    }
    
    http.open("DELETE","http://localhost:8080/POSSystem/customer",true); 
    http.setRequestHeader("Content-Type","application/json");
    http.send(customerJASON);

}

