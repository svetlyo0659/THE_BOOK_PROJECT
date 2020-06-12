package bg.codeacademy.spring.project1.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IdEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  public IdEntity()
  {

  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
}
