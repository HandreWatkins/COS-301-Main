package Analisis.ClientRule;

import Analisis.AnalisisInterface;

public class ClientRule implements AnalisisInterface
{
    public ClientRule()
    {
        
    }

    @Override
    public boolean testRequest(String[] data)
    {
        return true;
    }    
}
