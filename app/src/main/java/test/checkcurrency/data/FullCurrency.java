package test.checkcurrency.data;

public class FullCurrency
{
    private Stock[] stock;

    private String as_of;

    public Stock[] getStock ()
    {
        return stock;
    }

    public void setStock (Stock[] stock)
    {
        this.stock = stock;
    }

    public String getAs_of ()
    {
        return as_of;
    }

    public void setAs_of (String as_of)
    {
        this.as_of = as_of;
    }
}

