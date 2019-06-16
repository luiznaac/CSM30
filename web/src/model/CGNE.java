package model;

import org.jblas.FloatMatrix;

public class CGNE {

  private FloatMatrix f;
  private FloatMatrix r0;
  private FloatMatrix r1;
  private FloatMatrix p;
  
  public CGNE(FloatMatrix g) {
    r0 = g;
    f = FloatMatrix.zeros(3600);
    p = H.getH().transpose().mmul(r0);
  }
  
  public float iterate() {
    FloatMatrix a = alpha();
    f = f.add(a.mmul(p));
    r1 = r0.sub(a.mmul(H.getH().mmul(p)));
    FloatMatrix b = beta();
    p = H.getH().transpose().mmul(r1).add(b.mmul(p));
    r0 = r1;
    
    return r1.norm2();
  }
  
  private FloatMatrix beta() {
    FloatMatrix r0t = r0.transpose();
    FloatMatrix r1t = r1.transpose();
    
    FloatMatrix rr0 = r0t.mmul(r0);
    FloatMatrix rr1 = r1t.mmul(r1);
    
    return rr1.div(rr0);
  }
  
  private FloatMatrix alpha() {
    FloatMatrix rt = r0.transpose();
    FloatMatrix pt = p.transpose();
    
    FloatMatrix rr = rt.mmul(r0);
    FloatMatrix pp = pt.mmul(p);
    
    return rr.div(pp);
  }
  
  public FloatMatrix getF() {
    return f;
  }
  
}

