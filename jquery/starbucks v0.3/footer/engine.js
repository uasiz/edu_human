  	function email_check(){
        var str=document.getElementById("E_address").value;
        if(str.indexOf("@")==-1){
          document.getElementById("E_error").style.display='block';
        }
        else{
          document.getElementById("E_error").style.display='none';
            }
      }