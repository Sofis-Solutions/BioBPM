package lat.sofis.biobpm.run.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "biobpm.run")
public class BioBpmRunProperties {

  private BioBpmRunCorsProperties cors = new BioBpmRunCorsProperties();

  public BioBpmRunCorsProperties getCors() {
    return cors;
  }

  public void setCors(BioBpmRunCorsProperties cors) {
    this.cors = cors;
  }
}
