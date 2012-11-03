/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.client;

import dvd.business.client.AlbumManager;
import dvd.business.client.CollectionManager;
import dvd.business.client.DataStoreManager;
import dvd.entity.CollectionCate;
import dvd.entity.DataStore;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class playVideoMusicManager {
    private static int UserId;
    private static int albumId;
    private static int dataStoreId;
    private static int playListId;
    List<DataStore> listDS = null;
    private static String name;
    private static String date;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    
    /**
     * Creates a new instance of playVideoMusicManager
     */
    public playVideoMusicManager() {
        try {
            if (session.getAttribute("UserId") == null || session.getAttribute("UserId") == "") {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(DetailAlbumManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                UserId = Integer.parseInt("" + session.getAttribute("UserId"));
            }
        } catch (Exception e) {
        }
    }

    public void addIdAlbum(int _AlbumId) {
        if (_AlbumId != 0) {
            try {
                dataStoreId = 0;
                playListId = 0;
                albumId = _AlbumId;
                AlbumManager am = new AlbumManager();
                ResultSet rs = am.showInforAlbum(albumId);
                if (rs.next()) {
                    name = rs.getString(3);
                    date = "Create date: " + rs.getString(5);
                }
            } catch (SQLException ex) {
                Logger.getLogger(playVideoMusicManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addIdDatStore(int _DStoreId) {
        try {
            if (_DStoreId != 0) {
                dataStoreId = _DStoreId;
                albumId = 0;
                playListId = 0;
                DataStoreManager dsm = new DataStoreManager();
                List<DataStore> lds = dsm.returnDataStoreById(dataStoreId);
                for (DataStore dataStore : lds) {
                    name = dataStore.getDataName();
                    date = "";
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    public void addIdPlaylist(int _PlaylistId) {
        if (_PlaylistId != 0) {
            dataStoreId = 0;
            albumId = 0;
            playListId = _PlaylistId;
            CollectionManager cm = new CollectionManager();
            List<CollectionCate> listCC = cm.listCollectionCateById(_PlaylistId, UserId);
            for (CollectionCate collectionCate : listCC) {
                name = collectionCate.getCollectCateName();
                date = "Create date: " + collectionCate.getDateCreate();
            }
        }
    }

    public String loadDataVideoMusic() {
        String nameListFile = "";
        String sPathList = "";
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            if (albumId != 0) {
                AlbumManager am = new AlbumManager();
                listDS = am.showDataStore(albumId);
                nameListFile = extContext.getRealPath("DVDStore/Playlist/Album") + "\\Album" + albumId + ".xml";
                sPathList = "DVDStore/Playlist/Album/" + "Album" + albumId + ".xml";
                File file = new File(nameListFile);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } else if (dataStoreId != 0) {
                DataStoreManager dsm = new DataStoreManager();
                listDS = dsm.returnDataStoreById(dataStoreId);
                nameListFile = extContext.getRealPath("DVDStore/Playlist/DataStore") + "\\DataStore" + dataStoreId + ".xml";
                sPathList = "DVDStore/Playlist/DataStore/" + "DataStore" + dataStoreId + ".xml";
                File file = new File(nameListFile);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } else if (playListId != 0) {
                CollectionManager cm = new CollectionManager();
                listDS = cm.listDataStoreByPlaylistId(playListId);
                nameListFile = extContext.getRealPath("DVDStore/Playlist/Playlist") + "\\Playlist" + playListId + ".xml";
                sPathList = "DVDStore/Playlist/Playlist/" + "Playlist" + playListId + ".xml";
                File file = new File(nameListFile);
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            writerFileXML(nameListFile);
        } catch (Exception ex) {
        }
        return sPathList;
    }

    public boolean writerFileXML(String nameListFile) {
        try {
            //Write doc
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rssElement = doc.createElement("rss");
            rssElement.setAttribute("version", "2.0");
            rssElement.setAttribute("xmlns:jwplayer", "http://developer.longtailvideo.com/trac/");
            rssElement.setAttribute("xmlns:media", "http://search.yahoo.com/mrss/");

            Node channelNode = doc.createElement("channel");
            for (DataStore dataStore : listDS) {
                Node itemNode = doc.createElement("item");

                Node titleNode = doc.createElement("title");
                titleNode.setTextContent(dataStore.getDataName());
                itemNode.appendChild(titleNode);

                Element fileNode = doc.createElement("media:content");
                fileNode.setAttribute("url", dataStore.getDataPath());
                itemNode.appendChild(fileNode);

                Element thumbnailNode = doc.createElement("media:thumbnail");
                thumbnailNode.setAttribute("url", dataStore.getDataImage());
                itemNode.appendChild(thumbnailNode);

                Node imageNode = doc.createElement("jwplayer:playlist.image");
                imageNode.setTextContent(dataStore.getDataImage());
                itemNode.appendChild(imageNode);

                Node desNode = doc.createElement("description");
                desNode.setTextContent(dataStore.getDataDescription());
                itemNode.appendChild(desNode);

                channelNode.appendChild(itemNode);
            }
            rssElement.appendChild(channelNode);
            doc.appendChild(rssElement);
            writerXML(doc, nameListFile);
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean writerXML(Document _doc, String _url) {
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult stream = new StreamResult(new File(_url));
            DOMSource source = new DOMSource(_doc);
            trans.transform(source, stream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
}
