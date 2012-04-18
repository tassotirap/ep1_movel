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

        public List<menu> getMenu(int restaurantId, DateTime date)
        {
            var retorno = (from m in contexto.menus
                           from rm in m.restaurant_menu
                           where rm.date == date && rm.restaurant_id == restaurantId
                           select m).ToList();

            return retorno;
        }

        public List<restaurant> getRestaurants()
        {
            var retorno = (from r in contexto.restaurants
                           select r).ToList();
            return retorno;
        }
    }
}
