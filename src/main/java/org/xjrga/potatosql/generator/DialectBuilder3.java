package org.xjrga.potatosql.generator;

public class DialectBuilder3 implements Code
{

    private boolean isHsqldb;
    private CreateTestClass createTestClass;
    private boolean isTestClassSelected;


    public DialectBuilder3()
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

    public void setTestClassSelected(boolean selected)
    {
        isTestClassSelected = selected;
    }

    public boolean isTestClassSelected()
    {
        return isTestClassSelected;
    }

}
