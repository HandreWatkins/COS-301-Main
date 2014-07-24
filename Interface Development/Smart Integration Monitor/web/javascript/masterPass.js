function masterPass()
{
    pass = prompt('Please Enter The Master Password:','Master Password');
    if(pass === 'theansweris42')
    {
        window.location = "html/addUser.html";
    }
    else
    {
        masterPass();
    }
    
}