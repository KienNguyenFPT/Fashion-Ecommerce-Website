/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var updateStatus = document.getElementById("updateStatus");

updateStatus.addEventListener("click",()=>{
   updateStatus.addEventListener("change", ()=>{
       switch (updateStatus.value){
           case "paid":
               updateStatusOrder("paid");
               break;
           case "processing":
               updateStatusOrder("processing");
               break;
           case "complete":
               updateStatusOrder("complete");
               break;
       }
   }) 
});

function updateStatusOrder(s){
    dataToSend = {
        orderStatus: s
    };
    fetch('./updateStatusOrderController',{
        method: 'POST',
        headers:{
            'content-type': 'application/json'
        }, body: JSON.stringify(dataToSend)
    }).then(response =>{
        if (!response.ok){
            throw new Error("Network was not ok! Status: "+response.status);
        }else return response.text();
    }).then(data =>{
        if (data.toString() === "success"){
            var status = document.createElement("div");
            status.innerText = "Update Success!";
            document.getElementById("status-message").appendChild(status);
            window.setTimeout(function(){
                status.remove();
            },2000);
            document.getElementById("statusOrder").innerHTML = s;
        }else{
            throw new Error("Update fail!");
        }
    }).then(error =>{
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("status-message").appendChild(status);
        window.setTimeout(function(){
            status.remove();
        },2000);
    })
}
