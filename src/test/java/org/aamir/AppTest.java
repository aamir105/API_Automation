package org.aamir;

import io.restassured.response.Response;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class AppTest{
    String token = "\n" +
            "BQC5c0KKeOnobwfEaFrhkvb5XXuoxzpuu6aXBerJWBsML3oIKa4zB7a6VLXb4t1rHBIdOWr2tGafi6Ab06dGSm1tzdkLw5oG4wzQjFVl1Yb57pMbeMkVVamYohgFNM8HJmZe8wRjnWP4PGS1pz_iYZxJpJUCu1NwCZg6cseEU-1laPBR3SEbm8nwZ40oHXsjDfsFlwf5ohsyqDikQF3lKOKgyy6Qg42GEMRe7nHLyocNJZ5MXHk3wT12qtPEVTV9o2INraWvBQlbFb_YseIPQDDrHLp75AR927PuvX2BHr7yl-G3bkI98MVMvweFDXm8nSjD2Svf-ttqRTiADRiGj8o";

//    @Test
//    public void token(){
//        Response r = given()
//                .querxyParam("response_type","token")
//                .queryParam("client_id","47e0b50324c14163a27e9b2fc2e0be99")
//                .queryParam("scope","user-read-private, user-read-email")
//                .queryParam("redirect_url","http://localhost:3000")
//                .queryParam("state","state")
//                .when()
//                .post();
//    }

    @Test
    public void usres(){
        Response res = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+token)
                .when()
                .get("https://api.spotify.com/v1/me");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);

// If we want any specific name or value at that time we use path method
        String Id = res.path("id");
        Assert.assertEquals(Id,"31it6ppj3jflk6623vayww4iaw2m");

//If we want any value under the folder then
        String Id1 = res.path("external_urls.spotify");
        Assert.assertEquals(Id1,"31it6ppj3jflk6623vayww4iaw2m");

    }
//----------------------------------------------* (START USER) *--------------------------------------------------------------
    @Test
    public void GetCurrentUsersProfile(){
        Response res = given()
                .header("Accept","*/*")   //indicating that the client can accept any type of response.
                .header("Authorization","Bearer " +token)   //header with a Bearer token.
                                                                 // This token is typically used for authentication in APIs,
                                                                // ensuring the request is made by an authenticated user.
                .when()
                .get("https://api.spotify.com/v1/me");
        res.prettyPrint();
        String userid=res.path("id");
        res.then().statusCode(200);
    }

    @Test
    public void GetUsersTopItems(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/top/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetUsersProfile(){
        String userid = "aamir";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userid);
        res.prettyPrint();
        res.then().statusCode(200);
        String msg=res.path("display_name");
        Assert.assertEquals(msg,"aamir");
    }

    @Test
    public void FollowPlaylist(){
        String userplaylistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/playlists/"+ userplaylistid +"/followers");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void UnfollowPlaylist(){
        String userplaylistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/"+userplaylistid+"/followers");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetFollowedArtists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/following?type=artist");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void FollowArtistsorUsers(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2CIMQHirSU0MQqyYHq0eOx\",\n" +
                        "        \"57dN52uHvrHOxijzpIgu3E\",\n" +
                        "        \"1vCWHaC5f2uS3yhpwWbIA6\",\n" +   
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist");
        res.prettyPrint();
        res.then().statusCode(204);
    }

    @Test
    public void UnfollowArtistsorUsers(){
        String userplaylistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/"+userplaylistid+"/followers");
        res.prettyPrint();
        Assert.assertEquals(res.statusCode(),200);
    }

    @Test
    public void CheckIfUserFollowsArtistsorUsers(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx%2C57dN52uHvrHOxijzpIgu3E%2C1vCWHaC5f2uS3yhpwWbIA6");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void CheckifCurrentUserFollowsPlaylist(){
        String userplaylistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/playlists/"+userplaylistid+"/followers/contains");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END USER) *--------------------------------------------------------------

//----------------------------------------------* (START SEARCH) *--------------------------------------------------------------

    @Test
    public void search1(){
        Response res1 = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+token)
                .pathParam("q","Vijay")
                .pathParam("type","album")
                .when()
                .get("https://api.spotify.com/v1/search?q={q}&type={type}");
//                    https://api.spotify.com/v1/search?q=Vijay&type=album
        res1.prettyPrint();
        res1.then().assertThat().statusCode(200);
    }

    @Test
    public void search2(){
        Response res2 = given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+token)
                .queryParam("q","Vijay")
                .queryParam("type","album")
                .when()
                .get("https://api.spotify.com/v1/search");
//                    https://api.spotify.com/v1/search?q=Vijay&type=album
        res2.prettyPrint();
        res2.then().assertThat().statusCode(200);
    }

//----------------------------------------------* (END SEARCH) *--------------------------------------------------------------

//----------------------------------------------* (START MARKETS) *--------------------------------------------------------------
    @Test
    public void GetAvailableMarkets(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END MARKETS) *--------------------------------------------------------------

//----------------------------------------------* (START GENRE) *--------------------------------------------------------------
    @Test
    public void GetAvailableGenreSeeds(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END GENRE) *--------------------------------------------------------------

//----------------------------------------------* (START CHAPTER) *--------------------------------------------------------------

    @Test
    public void GetaChapter(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");
        res.prettyPrint();
        // res.then().statusCode(200);
    }

    @Test
    public void GetSeveralChapters(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/chapters?ids=0IsXVP0JmcB2adSE338GkK%2C3ZXb8FKZGU0EHALYX6uCzU%2C0D5wENdkdwbqlrHoaJ9g29");
        res.prettyPrint();
        // res.then().statusCode(200);
    }
//----------------------------------------------* (END CHAPTER) *--------------------------------------------------------------

//----------------------------------------------* (START CATEGORIES) *--------------------------------------------------------------
    @Test
    public void GetSeveralBrowseCategories(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories?locale=IN");
        res.prettyPrint();
        res.then().statusCode(200);
    }

        @Test
        public void GetSingleBrowseCategory(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/browse/categories/dinner");
            res.prettyPrint();
            res.then().statusCode(200);
        }
//----------------------------------------------* (START ARTIST) *--------------------------------------------------------------
    @Test
    @BeforeClass
    public void GetArtist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
        res.prettyPrint();
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        System.out.println("ArtistID   " + artistid);
        res.then().statusCode(200);
    }

    @Test
    public void GetSeveralArtists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 13)
    public void GetArtistsTopTracks(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/"+ artistid +"/top-tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 12)
    public void GetArtistsAlbums(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/"+ artistid +"/albums");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetArtistsRelatedArtists(){
        String artistid = "0TnOYISbd1XYRBk9myaseg";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/artists/" +artistid+ "/related-artists");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END ARTIST) *--------------------------------------------------------------

//----------------------------------------------* (START MARKETS) *--------------------------------------------------------------
    @Test
    @BeforeClass
    public void GetAlbum(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
        res.prettyPrint();
        String albumid=res.path("id");
        System.out.println(albumid);
        res.then().statusCode(200);
    }

        @Test(priority = 15)
        public void GetSeveralAlbums(){
            String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/albums?ids="+ albumid);
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 16)
        public void GetAlbumTracks(){
            String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get(" https://api.spotify.com/v1/albums/"+albumid+"/tracks");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 17)
        public void GetUsersSavedAlbums(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/albums");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 18)
        //    1kDZSmw3mKQeAjcmPTLS3M-og
        public void CheckUsersSavedAlbums(){
            String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/albums/contains?ids="+albumid);
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test
        public void GetNewReleases(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/browse/new-releases");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 19)
        //   1kDZSmw3mKQeAjcmPTLS3M
        public void SaveAlbumsforCurrentUser(){
            String albumid = "4aawyAB9vmqN3uQ7FjRGTy";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .put("https://api.spotify.com/v1/me/albums?ids="+albumid);
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test
        public void RemoveUsersSavedAlbums(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/albums?ids=382ObEPsp2rxGrnsizN5TX%2C1A2GTWGtFfWp7KSQTwWOyo%2C2noRn2Aes5aoNVsU6iWThc");
            res.prettyPrint();
            res.then().statusCode(200);
        }
//----------------------------------------------* (END ALBUMS) *--------------------------------------------------------------

//----------------------------------------------* (START AUDIOBOOKS) *--------------------------------------------------------------
    @Test
    @BeforeClass
    public void GetanAudiobook(){
        String audiobookid = "7iHfbu1YPACw6oZPAFJtqe";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci");
        res.prettyPrint();
        audiobookid = res.path("id");
        System.out.println(audiobookid);
        res.then().statusCode(200);
    }

        @Test(priority = 21)
        public void GetSeveralAudiobooks(){
            String audiobookid = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 24)
        public void GetUsersSavedAudiobooks(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/audiobooks");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 22)
        public void GetAudiobookChapters(){
            String audiobookid = "7iHfbu1YPACw6oZPAFJtqe";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 23)
        public void CheckUsersSavedAudiobooks(){
            String audiobookid = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .get("https://api.spotify.com/v1/me/audiobooks?ids="+audiobookid+",1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test(priority = 25)
        public void SaveAudiobooksforCurrentUser(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .body("{\"ids\": [\"18yVqkdbdRvS24c0Ilj2ci\", \"1HGw3J3NxZO1TP1BTtVhpZ\", \"7iHfbu1YPACw6oZPAFJtqe\"]}")
                    .put("https://api.spotify.com/v1/me/audiobooks");
            res.prettyPrint();
            res.then().statusCode(200);
        }

        @Test
        public void RemoveUsersSavedAudiobooks(){
            Response res = given()
                    .header("Accept","*/*")
                    .header("Authorization","Bearer " +token)
                    .when()
                    .delete("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");
            res.prettyPrint();
            res.then().statusCode(200);
        }

//----------------------------------------------* (END AUDIOBOOKS) *--------------------------------------------------------------

//----------------------------------------------* (START SHOW) *--------------------------------------------------------------
    @Test(priority = 26)
    public void GetSeveralShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    @BeforeClass
    public void GetShow(){
        String showid = "38bS44xjbVVZ3No3ByF1dJ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe");
        res.prettyPrint();
        showid=res.path("id");
        System.out.println(showid);
        res.then().statusCode(200);
    }

    @Test
    public void GetShowEpisodes(){
        String showid = "38bS44xjbVVZ3No3ByF1dJ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/"+showid+"/episodes");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetUsersSavedShows(){
        String showid = "38bS44xjbVVZ3No3ByF1dJ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids="+showid+",C5as3aKmN2k11yfDDDSrvaZ");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void SaveShowsforCurrentUser(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test//0sXfyB6o89daCtf4JkH7iP
    public void RemoveUsersSavedShows(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void CheckUsersSavedShows(){
        String showid = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains?ids="+showid);
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END SHOW) *--------------------------------------------------------------

//----------------------------------------------* (START TRACKS) *--------------------------------------------------------------

    @Test
    public void GetTrack(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks/5zCnGtCl5Ac5zlFHXaZmhy");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetSeveralTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void CheckUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void SaveTracksforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetSeveralTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void GetTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetRecommendations(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   RemoveUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END TRACKS) *--------------------------------------------------------------

//----------------------------------------------* (START PLAYLIST) *--------------------------------------------------------------

    @Test
    public void GetPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n");
        res.prettyPrint();
        String play=res.path("id");
        System.out.println(play);
        res.then().statusCode(200);
    }

    @Test
    public void ChangePlaylistDetails(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "   \n" +
                        "    \"description\": \"Updated playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetPlaylistItems(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   UpdatePlaylistItems(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"range_start\": 1,\n" +
                        "    \"insert_before\": 4,\n" +
                        "    \"range_length\": 2\n" +
                        "}\n")
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void AddItemstoPlaylist(){
        String playlistid = "3cEYpjA9oz9GiPac4AsH4n";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:5urYiIXu1ZhfMAOsp7WDTc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test
    public void GetCurrentUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/users/31nakixlkshe2banry5tx3d2hnoe/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   CreatePlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"name\": \"New Playlist\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/MyPLayList/playlists");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test
    public void   GetFeaturedPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetCategorysPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/images");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void  AddCustomPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("/9j/2wCEABoZGSccJz4lJT5CLy8vQkc9Ozs9R0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0cBHCcnMyYzPSYmPUc9Mj1HR0dEREdHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR//dAAQAAf/uAA5BZG9iZQBkwAAAAAH/wAARCAABAAEDACIAAREBAhEB/8QASwABAQAAAAAAAAAAAAAAAAAAAAYBAQAAAAAAAAAAAAAAAAAAAAAQAQAAAAAAAAAAAAAAAAAAAAARAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwAAARECEQA/AJgAH//Z")
                .when()
                .post("https://api.spotify.com/v1/playlists/0WioKBo6Sf6JLGz42Pr4Fv/images");
        res.prettyPrint();
        //  res.then().statusCode(200);
    }
//----------------------------------------------* (END PLAYLIST) *--------------------------------------------------------------

//----------------------------------------------* (START EPISODES) *--------------------------------------------------------------
    @Test
    @BeforeClass
    //exmaple 3na8lYc2MmdGtCCz5cs4KM    4w2p5chl38Mp35dAubmjzX-og
    public void   GetEpisode(){
        String episodeid = "512ojhOuo1ktJprKbVcKyQ";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes/2EtOmHpIHwfRefGGZbutMT");
        res.prettyPrint();
        episodeid=res.path("id");
        System.out.println("True");
        res.then().statusCode(200);
    }

    @Test
    public void   GetSeveralEpisodes(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void  SaveEpisodesforCurrentUser(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/episodes?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   CheckUsersSavedEpisodes(){
        String episodeid = "77o6BIVlYM3msb4MMIL1jH,0Q86acNRm6V9GYx55SXKwf";
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains?ids="+episodeid);
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void  RemoveUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/episodes?ids=3na8lYc2MmdGtCCz5cs4KM");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//----------------------------------------------* (END EPISODES) *--------------------------------------------------------------

//-------------------------------------------------* (START PLAYER) *------------------------------------------------------
    @Test
    public void  TransferPlayback(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"device_ids\": [\n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test
    public void   GetCurrentlyPlayingTrack() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   GetAvailableDevices() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test
    public void   StartResumePlayback() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                        "    \"offset\": {\n" +
                        "        \"position\": 5\n" +
                        "    },\n" +
                        "    \"position_ms\": 0\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test
    public void   GettheUsersQueue() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test
    public void   GetRecentlyPlayedTracks() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");
        res.prettyPrint();
        res.then().statusCode(200);
    }
}

