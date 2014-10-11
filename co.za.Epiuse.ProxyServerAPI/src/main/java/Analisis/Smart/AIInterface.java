package Analisis.Smart;

import Analisis.AnalisisInterface;

public class AIInterface implements AnalisisInterface
{
    public AIInterface()
    {
        
    }

    @Override
    public boolean testRequest(String[] data)
    {
        return false;
        
    }

    @Override
    public Long getexpected() {
        return null;
        
    } 
}
