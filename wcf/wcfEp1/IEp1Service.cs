using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace wcfEp1
{
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

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        void SetGate(int gateId, double distance);

        #endregion Gates

        #region Restaurants

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<Restaurant> GetRestaurants();

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        Restaurant GetRestaurant(int restaurantId);

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        void SetRestaurantComment(int RestaurantId, string comment, int status);

        [OperationContract]
        [WebInvoke(ResponseFormat = WebMessageFormat.Json, Method = "GET")]
        List<RestaurantComment> GetRestaurantComment(int RestaurantId, int qtde);

        #endregion Restaurants
    }


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
        int id, latitude, longitude, status;
        string name;

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

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
        private string url;
        private string clearURL;

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

        [DataMember]
        public string URL
        {
            get { return url; }
            set { url = value; }
        }

        [DataMember]
        public string ClearURL
        {
            get { return clearURL; }
            set { clearURL = value; }
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

        [DataMember]
        public string Comment
        {
            get;
            set;
        }

        [DataMember]
        public string Date
        {
            get;
            set;
        }

        [DataMember]
        public int Status
        {
            get;
            set;
        }

        [DataMember]
        public int RestaurantId
        {
            get;
            set;
        }
    }
}
