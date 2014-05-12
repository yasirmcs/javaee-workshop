package de.gedoplan.workshop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Talk extends GeneratedIntegerIdEntity
{
  @NotNull
  @Size(min = 5)
  private String       title;

  @ElementCollection(fetch = FetchType.LAZY)
  private List<String> speakers;

  //  @Enumerated(EnumType.STRING)
  @Convert(converter = TalkTypeConverter.class)
  @Column(columnDefinition = "CHAR(1)")
  private TalkType     talkType;

  @Temporal(TemporalType.TIMESTAMP)
  private Date         start;

  @Temporal(TemporalType.TIME)
  private Date         duration;

  @ManyToOne
  private SpecialDay   specialDay;

  public Talk(String title, TalkType talkType, Date start, Date duration, String... speakers)
  {
    this.title = title;
    this.talkType = talkType;
    this.start = start;
    this.duration = duration;
    this.speakers = new ArrayList<>();
    for (String speaker : speakers)
    {
      this.speakers.add(speaker);
    }
  }

  protected Talk()
  {
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public List<String> getSpeakers()
  {
    return this.speakers;
  }

  public TalkType getTalkType()
  {
    return this.talkType;
  }

  public void setTalkType(TalkType talkType)
  {
    this.talkType = talkType;
  }

  public Date getStart()
  {
    return this.start;
  }

  public void setStart(Date start)
  {
    this.start = start;
  }

  public Date getDuration()
  {
    return this.duration;
  }

  public void setDuration(Date duration)
  {
    this.duration = duration;
  }

  public String getSpeakersAsCommaSeparatedList()
  {
    StringBuilder sb = new StringBuilder();
    for (String speaker : this.speakers)
    {
      if (sb.length() != 0)
      {
        sb.append(", ");
      }
      sb.append(speaker);
    }

    return sb.toString();
  }

  public void setSpeakersAsCommaSeparatedList(String cs)
  {
    this.speakers.clear();
    for (String speaker : cs.split("\\s*,\\s*"))
    {
      this.speakers.add(speaker);
    }
  }

  public SpecialDay getSpecialDay()
  {
    return this.specialDay;
  }

  public void setSpecialDay(SpecialDay specialDay)
  {
    this.specialDay = specialDay;
  }

}
