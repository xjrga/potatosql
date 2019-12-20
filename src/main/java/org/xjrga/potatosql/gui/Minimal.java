package org.xjrga.potatosql.gui;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;
import java.awt.*;

public class Minimal extends MetalTheme
{

    private FontUIResource dejaVu_sans_mono = new FontUIResource("DejaVu Sans Mono", Font.PLAIN, 13);
    //gray
    private ColorUIResource colorUIResource0 = new ColorUIResource(0xf2f2f2);
    //black
    private ColorUIResource colorUIResource1 = new ColorUIResource(0, 0, 0);
    //blue
    private ColorUIResource colorUIResource2 = new ColorUIResource(0xd4f0fa);

    public Minimal(){
        UIManager.put("TabbedPane.focus", colorUIResource2);
        UIManager.put("TabbedPane.selected", colorUIResource2);
        UIManager.put("Button.select", colorUIResource2);
    }

    @Override
    public String getName()
    {
        return "Minimal";
    }

    @Override
    protected ColorUIResource getPrimary1()
    {
        return colorUIResource1;
    }

    @Override
    protected ColorUIResource getPrimary2()
    {
        //selected menu,menuitem background
        return colorUIResource2;
    }

    @Override
    protected ColorUIResource getPrimary3()
    {
        //selected field,row background
        return colorUIResource2;
    }

    @Override
    protected ColorUIResource getSecondary1()
    {
        return colorUIResource1;
    }

    @Override
    protected ColorUIResource getSecondary2()
    {
        //panel, table outlines
        return colorUIResource0;
    }

    @Override
    protected ColorUIResource getSecondary3()
    {
        //app background
        return colorUIResource0;
    }

    public ColorUIResource getFocusColor() {
        //selected field outline
        return colorUIResource0;
    }

    public ColorUIResource getMenuSelectedForeground() {
        //menu text when selected
        return colorUIResource1;
    }

    @Override
    public FontUIResource getControlTextFont()
    {
        return dejaVu_sans_mono;
    }

    @Override
    public FontUIResource getSystemTextFont()
    {
        return dejaVu_sans_mono;
    }

    @Override
    public FontUIResource getUserTextFont()
    {
        return dejaVu_sans_mono;
    }

    @Override
    public FontUIResource getMenuTextFont()
    {
        return dejaVu_sans_mono;
    }

    @Override
    public FontUIResource getWindowTitleFont()
    {
        return dejaVu_sans_mono;
    }

    @Override
    public FontUIResource getSubTextFont()
    {
        return dejaVu_sans_mono;
    }
}