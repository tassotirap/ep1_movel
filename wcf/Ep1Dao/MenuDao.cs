using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ep1Dao
{
    public class MenuDao
    {
        Ep1Entities contexto;

        public enum Type:int { Itens = 1, Desserts = 2, Drinks = 3 };

        public MenuDao(Ep1Entities contexto)
        {
            this.contexto = contexto;
        }
    }
}
