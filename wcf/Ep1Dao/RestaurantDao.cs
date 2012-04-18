using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ep1Dao
{
    public class RestaurantDao
    {
        Ep1Entities contexto;

        public RestaurantDao(Ep1Entities contexto)
        {
            this.contexto = contexto;
        }

        public void setComment(restaurants_posts comment)
        {
            contexto.AddTorestaurants_posts(comment);
            contexto.SaveChanges();
        }

        public List<restaurants_posts> getComment(int restaurantId)
        {
            var post = (from p in contexto.restaurants_posts
                        where p.restaurant_id == restaurantId
                        select p).ToList();

            return post;
        }
    }
}
