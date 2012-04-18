using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace wcfEp1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IEp1Service
    {
        #region Overlay
        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<Overlay> GetOverlays();

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        void SetOverlay(int latitude, int longitude, string name, int type);

        #endregion Overlay

        #region Gates
        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<Gate> GetGates();

        #endregion Gates

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        Menu GetMenu(int restaurantId, DateTime date);


        #region Restaurants

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<Restaurant> GetRestaurants();

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        void SetRestaurantComment(int RestaurantId, string comment, int status);

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<RestaurantComment> GetRestaurantComment(int RestaurantId);

        #endregion Restaurants
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class Overlay
    {
        int latitude, longitude, type;
        string name;

        [DataMember]
        public int Latitude
        {
            get { return latitude; }
            set { latitude = value; }
        }

        [DataMember]
        public int Longitude
        {
            get { return longitude; }
            set { longitude = value; }
        }

        [DataMember]
        public int Type
        {
            get { return type; }
            set { type = value; }
        }

        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }
    }

    [DataContract]
    public class Gate
    {
        int latitude, longitude, status;
        string name;

        [DataMember]
        public int Latitude
        {
            get { return latitude; }
            set { latitude = value; }
        }

        [DataMember]
        public int Longitude
        {
            get { return longitude; }
            set { longitude = value; }
        }

        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        [DataMember]
        public int Status
        {
            get { return status; }
            set { status = value; }
        }
    }

    [DataContract]
    public class Restaurant
    {
        private int id;
        private string name;
        private int status;

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        [DataMember]
        public int Status
        {
            get { return status; }
            set { status = value; }
        }
    }

    [DataContract]
    public class Menu
    {
        private List<string> itens;
        private List<string> desserts;
        private List<string> drinks;

        [DataMember]
        public List<string> Itens
        {
            get { return itens; }
            set { itens = value; }
        }

        [DataMember]
        public List<string> Desserts
        {
            get { return desserts; }
            set { desserts = value; }
        }

        [DataMember]
        public List<string> Drinks
        {
            get { return drinks; }
            set { drinks = value; }
        }
    }

    [DataContract]
    public class RestaurantComment
    {
        private string comment;

        [DataMember]
        public string Comment
        {
            get { return comment; }
            set { comment = value; }
        }
        private string date;

        [DataMember]
        public string Date
        {
            get { return date; }
            set { date = value; }
        }
    }
}
