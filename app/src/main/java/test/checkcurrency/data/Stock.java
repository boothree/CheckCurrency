package test.checkcurrency.data;

public class Stock
{
    private Price price;

    private String symbol;

    private String percent_change;

    private String name;

    private String volume;

    public Price getPrice ()
    {
        return price;
    }

    public void setPrice (Price price)
    {
        this.price = price;
    }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getPercent_change ()
    {
        return percent_change;
    }

    public void setPercent_change (String percent_change)
    {
        this.percent_change = percent_change;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getVolume ()
    {
        return volume;
    }

    public void setVolume (String volume)
    {
        this.volume = volume;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", symbol = "+symbol+", percent_change = "+percent_change+", name = "+name+", volume = "+volume+"]";
    }
}


