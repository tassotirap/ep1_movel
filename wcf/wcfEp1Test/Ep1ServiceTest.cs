using wcfEp1;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using Microsoft.VisualStudio.TestTools.UnitTesting.Web;

namespace wcfEp1Test
{
    
    
    /// <summary>
    ///This is a test class for Ep1ServiceTest and is intended
    ///to contain all Ep1ServiceTest Unit Tests
    ///</summary>
    [TestClass()]
    public class Ep1ServiceTest
    {


        private TestContext testContextInstance;

        /// <summary>
        ///Gets or sets the test context which provides
        ///information about and functionality for the current test run.
        ///</summary>
        public TestContext TestContext
        {
            get
            {
                return testContextInstance;
            }
            set
            {
                testContextInstance = value;
            }
        }

        #region Additional test attributes
        // 
        //You can use the following additional attributes as you write your tests:
        //
        //Use ClassInitialize to run code before running the first test in the class
        //[ClassInitialize()]
        //public static void MyClassInitialize(TestContext testContext)
        //{
        //}
        //
        //Use ClassCleanup to run code after all tests in a class have run
        //[ClassCleanup()]
        //public static void MyClassCleanup()
        //{
        //}
        //
        //Use TestInitialize to run code before running each test
        //[TestInitialize()]
        //public void MyTestInitialize()
        //{
        //}
        //
        //Use TestCleanup to run code after each test has run
        //[TestCleanup()]
        //public void MyTestCleanup()
        //{
        //}
        //
        #endregion


        /// <summary>
        ///A test for CalcularDistancia
        ///</summary>
        // TODO: Ensure that the UrlToTest attribute specifies a URL to an ASP.NET page (for example,
        // http://.../Default.aspx). This is necessary for the unit test to be executed on the web server,
        // whether you are testing a page, web service, or a WCF service.
        [TestMethod()]
        [HostType("ASP.NET")]
        [AspNetDevelopmentServerHost("C:\\Users\\Tasso\\documents\\visual studio 2010\\Projects\\wcfEp1\\wcfEp1", "/")]
        [UrlToTest("http://localhost:38330/")]
        [DeploymentItem("wcfEp1.dll")]
        public void CalcularDistanciaTest()
        {
            Ep1Service_Accessor target = new Ep1Service_Accessor(); // TODO: Initialize to an appropriate value
            int latitude1 = -23560634; // TODO: Initialize to an appropriate value
            int longitude1 = -46713035; // TODO: Initialize to an appropriate value
            int latitude2 = -23556732; // TODO: Initialize to an appropriate value
            int longitude2 = -46720632; // TODO: Initialize to an appropriate value
            target.CalcularDistancia(latitude1, longitude1, latitude2, longitude2);
            Assert.Inconclusive("A method that does not return a value cannot be verified.");
        }
    }
}
