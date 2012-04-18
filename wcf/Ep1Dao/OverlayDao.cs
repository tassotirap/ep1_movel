using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ep1Dao
{
    public class OverlayDao
    {

        Ep1Entities contexto;

        public OverlayDao(Ep1Entities contexto)
        {
            this.contexto = contexto;
        }

        public List<overlay> getOverlays()
        {
            List<overlay> lstOverlay = (from o in contexto.overlays
                                        where o.enable
                                        select o).ToList();

            return lstOverlay;
        }


        public void setOverlay(overlay ol)
        {
            try
            {
                contexto.AddTooverlays(ol);
                contexto.SaveChanges();
            }
            catch
            { }
        }


        
    }
}
