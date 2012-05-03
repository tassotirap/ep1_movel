﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Ep1Dao;
using MySql.Data.MySqlClient;
using System.Data;

namespace wcfEp1
{
    public class Ep1Service : IEp1Service
    {
        #region variable

        OverlayDao overlayDao;
        GatesDao gatesDao;
        MenuDao menuDao;
        RestaurantDao restaurantDao;
        Ep1Entities contexto;

        #endregion variable

        public Ep1Service()
        {
            contexto = new Ep1Entities();
            overlayDao = new OverlayDao(contexto);
            menuDao = new MenuDao(contexto);
            gatesDao = new GatesDao(contexto);
            restaurantDao = new RestaurantDao(contexto);
        }

        #region Overlay

        public List<Overlay> GetOverlays()
        {
            List<Overlay> lstOverlay = new List<Overlay>();

            try
            {

                List<overlay> lstOverlayData = overlayDao.getOverlays();

                foreach (overlay o in lstOverlayData)
                {
                    lstOverlay.Add(new Overlay
                    {
                        Latitude = o.latitude,
                        Longitude = o.longitude,
                        Type = o.type_id,
                        Name = o.name
                    });
                }

                return lstOverlay;
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        public void SetOverlay(int latitude, int longitude, string name, int type)
        {
            overlay ol = new overlay();
            ol.latitude = latitude;
            ol.longitude = longitude;
            ol.name = name;
            ol.type_id = type;
            ol.enable = false;

            overlayDao.setOverlay(ol);
        }

        #endregion Overlay

        #region Gates

        public List<Gate> GetGates()
        {
            try
            {
                DateTime dtIni = DateTime.Now.AddMinutes(-5);
                List<gate> gates = gatesDao.getGates();
                List<Gate> lstGates = new List<Gate>();

                foreach (gate gate in gates)
                {
                    double distancia = 0;
                    List<gate_posts> posts = gatesDao.getGatePostByIdAndTime(gate.id, dtIni, DateTime.Now);

                    if (posts.Count > 0)
                    {
                        foreach (gate_posts post in posts)
                            distancia += Math.Abs(CalcularDistancia(gate.latitude, gate.longitude, post.latitude, post.longitude));

                        distancia = distancia / posts.Count;

                        if (distancia > 3)
                            gate.status_id = 3;
                        else if (distancia > 2)
                            gate.status_id = 2;
                        else
                            gate.status_id = 1;

                        gatesDao.saveGate(gate);
                    }

                    lstGates.Add(new Gate
                    {
                        Latitude = gate.latitude,
                        Longitude = gate.longitude,
                        Name = gate.name,
                        Status = gate.status_id
                    });
                }

                return lstGates;
            }
            catch (Exception e)
            {
                throw e;
            }
        }

        private double CalcularDistancia(int latitude1, int longitude1, int latitude2, int longitude2)
        {
            double dLatitude1 = ConvertCoord(latitude1);
            double dLongitude1 = ConvertCoord(longitude1);
            double dLatitude2 = ConvertCoord(latitude2);
            double dLongitude2 = ConvertCoord(longitude2);

            double distancia = 6371 *
                Math.Acos(Math.Cos(Math.PI * (90 - dLatitude1) / 180) * Math.Cos((90 - dLatitude2) *
                Math.PI / 180) + Math.Sin((90 - dLatitude1) * Math.PI / 180) * Math.Sin((90 - dLatitude2) * Math.PI / 180) *
                Math.Cos((dLongitude2 - dLongitude1) * Math.PI / 180));

            return distancia;

        }

        private double ConvertCoord(int coord)
        {
            string coordStr = Math.Abs(coord).ToString();
            double retorno = 0;
            if (coordStr.Length >= 6)
                retorno = Convert.ToDouble(String.Concat(coordStr[0], coordStr[1])) + Convert.ToDouble(String.Concat(coordStr[2], coordStr[3])) / 60 + Convert.ToDouble(String.Concat(coordStr[4], coordStr[5])) / 3600;

            if (coord < 0)
                return -1 * retorno;

            return retorno;
        }

        #endregion Gates

        #region Restaurant

        public List<Restaurant> GetRestaurants()
        {
            var restaurants = restaurantDao.getRestaurants();

            List<Restaurant> restaurantsDto = new List<Restaurant>();

            foreach (restaurant restaurant in restaurants)
            {
                restaurantsDto.Add(new Restaurant
                {
                    Id = restaurant.id,
                    Name = restaurant.name,
                    Status = restaurant.status_id,
                    URL = restaurant.url,
                    ClearURL = restaurant.clear_url
                });
            }

            return restaurantsDto;
        }

        public Restaurant GetRestaurant(int restaurantId)
        {
            restaurant restaurante = restaurantDao.getRestaurant(restaurantId);

            DateTime dtFim = DateTime.Now;
            DateTime dtIni = dtFim.AddMinutes(-15);

            int status = 1;

            var posts = restaurantDao.getComment(restaurantId, dtIni, dtFim);
            if (posts.Count > 5)
            {
                status = (int)posts.Average(t => t.status_id);
            }

            restaurante.status_id = status;


            restaurantDao.setRestaurant(restaurante);


            return new Restaurant
            {
                Id = restaurante.id,
                Name = restaurante.name,
                Status = restaurante.status_id,
                URL = restaurante.url,
                ClearURL = restaurante.clear_url
            };
        }
        
        public void SetRestaurantComment(int restaurantId, string comment, int status)
        {
            restaurants_posts post = new restaurants_posts();
            post.restaurant_id = restaurantId;
            post.comment = comment;
            post.status_id = status;
            post.date = DateTime.Now;
            restaurantDao.setComment(post);
        }

        public List<RestaurantComment> GetRestaurantComment(int restaurantId, int qtde)
        {
            List<RestaurantComment> comments = new List<RestaurantComment>();

            var posts = restaurantDao.getComment(restaurantId, qtde);

            foreach (restaurants_posts post in posts)
            {
                comments.Add(new RestaurantComment
                {
                    RestaurantId = post.restaurant_id,
                    Comment = post.comment,
                    Date = post.date.ToString("yyyy-MM-dd hh:mm:ss"),
                    Status = post.status_id
                });
            }

            return comments;
        }
        
        #endregion Restaurant
    }
}
