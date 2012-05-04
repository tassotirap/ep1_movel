using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ep1Dao
{
    public class GatesDao
    {
        Ep1Entities contexto;

        public GatesDao(Ep1Entities contexto)
        {
            this.contexto = contexto;
        }

        public List<gate_posts> getGatePostByIdAndTime(int id, DateTime dateIni, DateTime dateEnd)
        {
            List<gate_posts> posts = (from p in contexto.gate_posts
                                      where dateIni <= p.date && p.date <= dateEnd && p.gate_id == id
                                      select p).ToList();

            return posts;
        }

        public List<gate> getGates()
        {
            List<gate> gates = (from g in contexto.gates
                                select g).ToList();

            return gates;
        }

        public void saveGate(gate gate)
        {
            if (gate.EntityKey == null)
                contexto.AddTogates(gate);

            contexto.SaveChanges();
        }

        public void saveGatePost(gate_posts gate_post)
        {
            if (gate_post.EntityKey == null)
                contexto.AddTogate_posts(gate_post);

            contexto.SaveChanges();
        }
    }
}
