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

        public List<restaurants_posts> getComment(int restaurantId, int qtde)
        {
            var post = (from p in contexto.restaurants_posts
                        where p.restaurant_id == restaurantId
                        orderby p.date descending
                        select p).Take(qtde).ToList();

            return post;
        }

        public List<restaurants_posts> getComment(int restaurantId, DateTime dtIni, DateTime dtFim)
        {
            var post = (from p in contexto.restaurants_posts
                        where p.restaurant_id == restaurantId
                        && dtIni <= p.date && p.date <= dtFim
                        orderby p.date descending
                        select p).ToList();
            return post;
        }

        public List<restaurant> getRestaurants()
        {
            var retorno = (from r in contexto.restaurants
                           select r).ToList();
            return retorno;
        }

        public restaurant getRestaurant(int restaurantId)
        {
            var retorno = (from r in contexto.restaurants
                           where r.id == restaurantId
                           select r).FirstOrDefault();
            return retorno;
        }

        public void setRestaurant(restaurant restaurant)
        {
            contexto.SaveChanges();
        }
    }
}
