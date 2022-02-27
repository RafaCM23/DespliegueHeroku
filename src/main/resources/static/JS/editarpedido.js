let mas = document.querySelectorAll(".mas");
let menos = document.querySelectorAll(".menos");



window.onload = function() {

for (let i = 0; i < mas.length; i++) {
   mas[i].addEventListener("click", sumar);
    
}
for (let i = 0; i < menos.length; i++) {
    menos[i].addEventListener("click", restar);
     
 }
}


function sumar(e){
  let id=e.target.id;
  let input= document.querySelector("input[id='"+id+"']");
  if(input.value<20){
  input.value=1*input.value+1;
  }
}

function restar(e){
  let id=e.target.id;
  let input= document.querySelector("input[id='"+id+"']");
  if(input.value>0){
  input.value=1*input.value-1;
  }
}

