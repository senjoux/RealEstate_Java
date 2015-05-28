/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Entity.LoginE;

/**
 *
 * @author FATHALLAH Wael
 */

public interface ILoginDAO {
      public int Login(String mail, String pass);
      public LoginE LoginU(String mail, String pass);
}
