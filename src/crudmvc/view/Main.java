package crudmvc.view;

import crudmvc.controller.UsuarioControl;

/**
 * @author Wellyson Martins
 */
public class Main
{
    
    public static void main(String[] args)
    {
        ViewMenu menu = new ViewMenu();
        UsuarioControl control = new UsuarioControl(menu);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
