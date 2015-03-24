/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appriver.program.pkgfinal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Squidward5
 */
@Entity
@Table(name = "INFORMATION")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Information.findAll", query = "SELECT i FROM Information i"),
    @NamedQuery(name = "Information.findByDates", query = "SELECT i FROM Information i WHERE i.dates = :dates"),
    @NamedQuery(name = "Information.findByTimes", query = "SELECT i FROM Information i WHERE i.times = :times"),
    @NamedQuery(name = "Information.findByMsgclass", query = "SELECT i FROM Information i WHERE i.msgclass = :msgclass"),
    @NamedQuery(name = "Information.findByUniquemsgid", query = "SELECT i FROM Information i WHERE i.uniquemsgid = :uniquemsgid"),
    @NamedQuery(name = "Information.findBySendingip", query = "SELECT i FROM Information i WHERE i.sendingip = :sendingip"),
    @NamedQuery(name = "Information.findByReceivingip", query = "SELECT i FROM Information i WHERE i.receivingip = :receivingip"),
    @NamedQuery(name = "Information.findByOriginalcountry", query = "SELECT i FROM Information i WHERE i.originalcountry = :originalcountry"),
    @NamedQuery(name = "Information.findByTestsfailed", query = "SELECT i FROM Information i WHERE i.testsfailed = :testsfailed")
})
public class Information implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DATES")
    @Temporal(TemporalType.DATE)
    private Date dates;
    @Basic(optional = false)
    @Column(name = "TIMES")
    @Temporal(TemporalType.TIME)
    private Date times;
    @Basic(optional = false)
    @Column(name = "MSGCLASS")
    private String msgclass;
    @Id
    @Basic(optional = false)
    @Column(name = "UNIQUEMSGID")
    private String uniquemsgid;
    @Basic(optional = false)
    @Column(name = "SENDINGIP")
    private String sendingip;
    @Basic(optional = false)
    @Column(name = "RECEIVINGIP")
    private String receivingip;
    @Basic(optional = false)
    @Column(name = "ORIGINALCOUNTRY")
    private String originalcountry;
    @Basic(optional = false)
    @Column(name = "TESTSFAILED")
    private String testsfailed;

    public Information()
    {
    }

    public Information(String uniquemsgid)
    {
        this.uniquemsgid = uniquemsgid;
    }

    public Information(String uniquemsgid, Date dates, Date times, String msgclass, String sendingip, String receivingip, String originalcountry, String testsfailed)
    {
        this.uniquemsgid = uniquemsgid;
        this.dates = dates;
        this.times = times;
        this.msgclass = msgclass;
        this.sendingip = sendingip;
        this.receivingip = receivingip;
        this.originalcountry = originalcountry;
        this.testsfailed = testsfailed;
    }

    public Date getDates()
    {
        return dates;
    }

    public void setDates(Date dates)
    {
        this.dates = dates;
    }

    public Date getTimes()
    {
        return times;
    }

    public void setTimes(Date times)
    {
        this.times = times;
    }

    public String getMsgclass()
    {
        return msgclass;
    }

    public void setMsgclass(String msgclass)
    {
        this.msgclass = msgclass;
    }

    public String getUniquemsgid()
    {
        return uniquemsgid;
    }

    public void setUniquemsgid(String uniquemsgid)
    {
        this.uniquemsgid = uniquemsgid;
    }

    public String getSendingip()
    {
        return sendingip;
    }

    public void setSendingip(String sendingip)
    {
        this.sendingip = sendingip;
    }

    public String getReceivingip()
    {
        return receivingip;
    }

    public void setReceivingip(String receivingip)
    {
        this.receivingip = receivingip;
    }

    public String getOriginalcountry()
    {
        return originalcountry;
    }

    public void setOriginalcountry(String originalcountry)
    {
        this.originalcountry = originalcountry;
    }

    public String getTestsfailed()
    {
        return testsfailed;
    }

    public void setTestsfailed(String testsfailed)
    {
        this.testsfailed = testsfailed;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (uniquemsgid != null ? uniquemsgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Information))
        {
            return false;
        }
        Information other = (Information) object;
        if ((this.uniquemsgid == null && other.uniquemsgid != null) || (this.uniquemsgid != null && !this.uniquemsgid.equals(other.uniquemsgid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "appriver.program.pkgfinal.Information[ uniquemsgid=" + uniquemsgid + " ]";
    }
    
}
