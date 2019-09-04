package org.xjrga.potatosql.generator;

public class Generator0 implements Code
{

    private boolean isHsqldb;
    private CreateTestClass createTestClass;
    private boolean isTestClassSelected;


    public Generator0()
    {
    }

    @Override
    public String getCode()
    {
        StringBuilder sb = new StringBuilder();

        if (isHsqldb)
        {
            sb.append(getHsqldb());

        }

        return sb.toString();
    }


    public String getHsqldb()
    {
        StringBuilder sb = new StringBuilder();

        if (isTestClassSelected())
        {
            createTestClass = new CreateTestClass();
            String code = createTestClass.getCode();
            sb.append(code);
            sb.append("\n");
            sb.append("\n");

        }

        return sb.toString();
    }


    public void setHsqldb(boolean hsqldb)
    {

        isHsqldb = hsqldb;
    }

    public boolean isTestClassSelected()
    {
        return isTestClassSelected;
    }

    public void setTestClassSelected(boolean selected)
    {
        isTestClassSelected = selected;
    }

}
