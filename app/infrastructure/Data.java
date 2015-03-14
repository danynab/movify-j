package infrastructure;

import models.Genre;
import models.Movie;
import models.Subscription;
import models.User;

import java.util.Random;

/**
 * Created by Dani on 13/3/15.
 */
public class Data {

    private static final String URL_BASE = "http://156.35.95.67/movifyj/assets/";
    private static final String URL_BASE2 = "http://156.35.95.67/movify/static/";

    private static Data instance;

    public static Data getInstance() {
        if (instance == null)
            instance = new Data();
        return instance;
    }

    private Data() {

    }

    public void init() {
        for (Subscription subscription : subscriptions) {
            subscription.save();
        }

        for (Genre genre : genres) {
            genre.setImage(URL_BASE + "genres/" + genre.getImage());
            genre.save();
        }

        relationsMovieGenre();
        User dani = Factories.businessFactory.getUserService().signUp("dani", "dani", "dani@movify.com");
        User david = Factories.businessFactory.getUserService().signUp("david", "david", "david@movify.com");
        User marco = Factories.businessFactory.getUserService().signUp("marco", "marco", "marco@movify.com");
        User luis = Factories.businessFactory.getUserService().signUp("luis", "luis", "luis@movify.com");
        User pepe = Factories.businessFactory.getUserService().signUp("pepe", "pepe", "pepe@movify.com");


        Random r = new Random();

        for (Movie movie : movies) {
            movie.setBackground(URL_BASE + "background/" + movie.getBackground());
            movie.setCover(URL_BASE + "covers/" + movie.getCover());
            movie.setMovie(URL_BASE2 + "movies/" + movie.getMovie());
            movie.setMovie(URL_BASE2 + "trailers/" + movie.getTrailer());
            movie.save();

            Factories.businessFactory.getReviewService().rateMovie(dani.getUsername(), movie.getId(), comment1, r.nextInt(6));
            Factories.businessFactory.getReviewService().rateMovie(david.getUsername(), movie.getId(), comment2, r.nextInt(6));
            Factories.businessFactory.getReviewService().rateMovie(marco.getUsername(), movie.getId(), comment3, r.nextInt(6));
            Factories.businessFactory.getReviewService().rateMovie(pepe.getUsername(), movie.getId(), comment4, r.nextInt(6));
            Factories.businessFactory.getReviewService().rateMovie(luis.getUsername(), movie.getId(), comment5, r.nextInt(6));
        }


    }


    private Subscription subscription1 = new Subscription(
            "1 month",
            "All movies you love for one month more. You in?",
            1,
            7.99
    );

    private Subscription subscription2 = new Subscription(
            "3 months",
            "Not enough? Add 3 months to your subscription",
            3,
            19.97
    );

    private Subscription subscription3 = new Subscription(
            "1 year",
            "This is just for truly movie lovers. Are you?",
            12,
            74.89
    );

    private Subscription[] subscriptions = new Subscription[]{subscription1, subscription2, subscription3};


    private Genre action = new Genre(
            "action",
            "action.jpg"
    );

    private Genre adventure = new Genre(
            "adventure",
            "adventure.jpg"
    );

    private Genre animation = new Genre(
            "animation",
            "animation.jpg"
    );

    private Genre biography = new Genre(
            "biography",
            "biography.jpg"
    );

    private Genre comedy = new Genre(
            "comedy",
            "comedy.jpg"
    );

    private Genre crime = new Genre(
            "crime",
            "crime.jpg"
    );

    private Genre drama = new Genre(
            "drama",
            "drama.jpg"
    );

    private Genre fantasy = new Genre(
            "fantasy",
            "fantasy.jpg"
    );

    private Genre mystery = new Genre(
            "mystery",
            "mystery.jpg"
    );

    private Genre scifi = new Genre(
            "sci-fi",
            "sci-fi.jpg"
    );

    private Genre thriller = new Genre(
            "thriller",
            "thriller.jpg"
    );

    private Genre war = new Genre(
            "war",
            "war.jpg"
    );


    private Genre[] genres = new Genre[]{
            action, adventure, animation, biography, comedy, crime,
            drama, fantasy, mystery, scifi, thriller, war};


    private Movie movie1 = new Movie(
            "Avengers: Age of Ultron",
            2015,
            150,
            "When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and it is up to the Avengers to stop the villainous Ultron from enacting his terrible plans.",
            "When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and Earth's Mightiest Heroes, including Iron Man, Captain America, Thor, The Incredible Hulk, Black Widow and Hawkeye, are put to the ultimate test as the fate of the planet hangs in the balance. As the villainous Ultron emerges, it is up to The Avengers to stop him from enacting his terrible plans, and soon uneasy alliances and unexpected action pave the way for a global adventure.",
            "Joss Whedon",
            "Joss Whedon, Stan Lee (comic book)",
            "Robert Downey Jr., Chris Evans, Mark Ruffalo",
            "sDQgTHVALB3y687Xn9MWtVDC.jpg",
            "WW78ZFqaF56h8GH2uKEHKdfL.jpg",
            "xGjhukFzGm8ScfAFJupGGNHB.mp4",
            "xGjhukFzGm8ScfAFJupGGNHB.mp4");


    private Movie movie2 = new Movie(
            "Kingsman: The Secret Service",
            2014,
            129,
            "A spy organization recruits an unrefined, but promising street kid into the agency's ultra-competitive training program, just as a global threat emerges from a twisted tech genius.",
            "Based upon the acclaimed comic book and directed by Matthew Vaughn, Kingsman: The Secret Service tells the story of a super-secret spy organization that recruits an unrefined but promising street kid into the agency's ultra-competitive training program just as a global threat emerges from a twisted tech genius",
            "Matthew Vaughn",
            "Jane Goldman (screenplay), Matthew Vaughn (screenplay)",
            "Colin Firth, Taron Egerton, Samuel L. Jackson",
            "9tTJz8D4xYQK2tp97xMQNccC.jpg",
            "4RZwY6HweJfbRYETsE4QSEZk.jpg",
            "PhJxBnKjeGFJ8y5DPS6Sjkg4.mp4",
            "PhJxBnKjeGFJ8y5DPS6Sjkg4.mp4");

    private Movie movie3 = new Movie(
            "Chappie",
            2015,
            120,
            "In the near future, crime is patrolled by a mechanized police force. When one police droid, Chappie, is stolen and given new programming, he becomes the first robot with the ability to think and feel for himself.",
            "In the near future, crime is patrolled by an oppressive mechanized police force. But now, the people are fighting back. When one police droid, Chappie, is stolen and given new programming, he becomes the first robot with the ability to think and feel for himself. As powerful, destructive forces start to see Chappie as a danger to mankind and order, they will stop at nothing to maintain the status quo and ensure that Chappie is the last of his kind.",
            "Neill Blomkamp",
            "Neill Blomkamp, Terri Tatchell",
            "Sharlto Copley, Dev Patel, Hugh Jackman",
            "L3GdHjgBC2wsZEenhumQgdKn.jpg",
            "buyjuAkQDSUb9DPaJpRRXd2t.jpg",
            "9VdWVDXhSgG4c5NRpaBrzWgm.mp4",
            "9VdWVDXhSgG4c5NRpaBrzWgm.mp4");

    private Movie movie4 = new Movie(
            "Big Hero 6",
            2014,
            102,
            "The special bond that develops between plus-sized inflatable robot Baymax, and prodigy Hiro Hamada, who team up with a group of friends to form a band of high-tech heroes.",
            "From Walt Disney Animation Studios, the team behind 'Frozen' and 'Wreck-It Ralph,' comes 'Big Hero 6,' an action-packed comedy-adventure about the special bond that develops between Baymax, a plus-sized inflatable robot, and prodigy Hiro Hamada. When a devastating event befalls the city of San Fransokyo and catapults Hiro into the midst of danger, he turns to Baymax and his close friends adrenaline junkie Go Go Tomago, neatnik Wasabi, chemistry whiz Honey Lemon and fanboy Fred. Determined to uncover the mystery, Hiro transforms his friends into a band of high-tech heroes called 'Big Hero 6.'",
            "Don Hall, Chris Williams",
            "Jordan Roberts (screenplay), Daniel Gerson (screenplay)",
            "Ryan Potter, Scott Adsit, Jamie Chung",
            "h6CvveAfUNBextzkxvzUPMJq.jpg",
            "vCF6pbGGkL4ktjJQdWnNK2HA.jpg",
            "d37abNvu2uvMPadRfck99Tyn.mp4",
            "d37abNvu2uvMPadRfck99Tyn.mp4");

    private Movie movie5 = new Movie(
            "American Sniper",
            2014,
            132,
            "Navy SEAL sniper Chris Kyle's pinpoint accuracy saves countless lives on the battlefield and turns him into a legend. Back home to his wife and kids after four tours of duty, however, Chris finds that it is the war he can't leave behind.",
            "Chris Kyle was nothing more than a Texan man who wanted to become a cowboy, but in his thirties he found out that maybe his life needed something different, something where he could express his real talent, something that could help America in its fight against terrorism. So he joined the SEALs in order to become a sniper. After marrying, Kyle and the other members of the team are called for their first tour of Iraq. Kyle's struggle isn't with his missions, but about his relationship with the reality of the war and, once returned at home, how he manages to handle it with his urban life, his wife and kids.",
            "Clint Eastwood",
            "Jason Hall, Chris Kyle (book)",
            "Bradley Cooper, Sienna Miller, Kyle Gallner",
            "ZejCEjDhV4GJXWbN8VRWKpfP.jpg",
            "Hpm67cWJwqJ4ztxphX7WqhrF.jpg",
            "e2y8qWFqR2QCkfffqekVR97j.mp4",
            "e2y8qWFqR2QCkfffqekVR97j.mp4");

    private Movie movie6 = new Movie(
            "Exodus: Gods and Kingsman",
            2014,
            150,
            "The defiant leader Moses rises up against the Egyptian Pharaoh Ramses, setting 600,000 slaves on a monumental journey of escape from Egypt and its terrifying cycle of deadly plagues.",
            "Epic adventure Exodus: Gods and Kings is the story of one man's daring courage to take on the might of an empire. Using state of the art visual effects and 3D immersion, Scott brings new life to the story of the defiant leader Moses as he rises up against the Egyptian Pharaoh Ramses, setting 600,000 slaves on a monumental journey of escape from Egypt and its terrifying cycle of deadly plagues.",
            "Ridley Scott",
            "Adam Cooper, Bill Collage",
            "Christian Bale, Joel Edgerton, Ben Kingsley",
            "sABWBnndZwRRAm9cMh7UarXx.jpg",
            "5ZKNreqVVqwqFAvjEMCPmkaX.jpg",
            "rxfmAQHXD9RxKmfVCrxp2Hg7.mp4",
            "rxfmAQHXD9RxKmfVCrxp2Hg7.mp4");

    private Movie movie7 = new Movie(
            "Jupiter Ascending",
            2015,
            127,
            "In a bright and colorful future, a young destitute caretaker gets targeted by the ruthless son of a powerful family, who lives on a planet in need of a new heir, so she travels with a genetically engineered warrior to the planet in order to stop his tyrant reign.",
            "Jupiter Jones was born under a night sky, with signs predicting that she was destined for great things. Now grown, Jupiter dreams of the stars but wakes up to the cold reality of a job cleaning other people's houses and an endless run of bad breaks. Only when Caine Wise, a genetically engineered ex-military hunter, arrives on Earth to track her down does Jupiter begin to glimpse the fate that has been waiting for her all along - her genetic signature marks her as next in line for an extraordinary inheritance that could alter the balance of the cosmos.",
            "Andy Wachowski (as The Wachowskis) , Lana Wachowski (as The Wachowskis)",
            "Andy Wachowski (as The Wachowskis) , Lana Wachowski (as The Wachowskis)",
            "Channing Tatum, Mila Kunis, Eddie Redmayne",
            "HegZdb4DT5Xn4QmpdBWp3Vy6.jpg",
            "vcSDDmFBXx7DbB6djQfwQuKC.jpg",
            "3ESZQnNa7TWKtHXNGd6FyJYb.mp4",
            "3ESZQnNa7TWKtHXNGd6FyJYb.mp4");

    private Movie movie8 = new Movie(
            "Kill Me Three Times",
            2014,
            90,
            "Professional hit-man Charlie Wolfe finds himself in three tales of murder, blackmail and revenge after a botched contract assignment.",
            "Professional hit-man Charlie Wolfe finds himself in three tales of murder, blackmail and revenge after a botched contract assignment.",
            "Kriv Stenders",
            "James McFarland",
            "Teresa Palmer, Simon Pegg, Luke Hemsworth",
            "qWAkF53X7hcknDdhmKR8rkhR.jpg",
            "e3nWM6cg7fMJH7da6FeHhS5E.jpg",
            "eZnYpMPVPdQ2CnJyLn6fZAkZ.mp4",
            "eZnYpMPVPdQ2CnJyLn6fZAkZ.mp4");

    private Movie movie9 = new Movie(
            "Furious 7",
            2015,
            140,
            "Deckard Shaw seeks revenge against Dominic Toretto and his family for the death of his brother.",
            "Deckard Shaw seeks revenge against Dominic Toretto and his family for the death of his brother.",
            "James Wan",
            "Chris Morgan, Gary Scott Thompson (characters)",
            "Vin Diesel, Paul Walker, Dwayne Johnson",
            "eNvmqqVV7AJpq6vCgRFGAFqQ.jpg",
            "XvtdhFY72tQQAGjpPkcdxpq9.jpg",
            "uXnt4NvFgsS2uV54C48CAgrq.mp4",
            "uXnt4NvFgsS2uV54C48CAgrq.mp4");

    private Movie movie10 = new Movie(
            "Fury",
            2014,
            134,
            "April, 1945. As the Allies make their final push in the European Theatre, a battle-hardened Army sergeant named Wardaddy commands a Sherman tank and his five-man crew on a deadly mission behind enemy lines. Outnumbered, out-gunned, and with a rookie soldier thrust into their platoon, Wardaddy and his men face overwhelming odds in their heroic attempts to strike at the heart of Nazi Germany.",
            "April, 1945. As the Allies make their final push in the European Theatre, a battle-hardened Army sergeant named Wardaddy (Brad Pitt) commands a Sherman tank and his five-man crew on a deadly mission behind enemy lines. Outnumbered, and out-gunned, Wardaddy and his men face overwhelming odds in their heroic attempts to strike at the heart of Nazi Germany.",
            "David Ayer",
            "David Ayer",
            "Brad Pitt, Shia LaBeouf, Logan Lerman",
            "EdnRKspxGKL4dVuYwjew6AdG.jpg",
            "4XCUX6JHQzStYcMfMhrdpMtW.jpg",
            "SVerMyyGUcj78MnTLruCsLJb.mp4",
            "SVerMyyGUcj78MnTLruCsLJb.mp4");

    private Movie movie11 = new Movie(
            "John Wick",
            2014,
            101,
            "An ex-hitman comes out of retirement to track down the gangsters that took everything from him.",
            "John Wick is a mob hit man who, upon falling in love, quits. 5 years later, his wife dies and to make sure he's not alone she arranges for a dog to be brought to him after her death. Later, some men wanting his car break in and beat him up and kill his dog. When he recovers, he sets to get the ones who killed his dog. He learns that the leader is the son of his former employer. And the man wanting to protect his son, tries to take care of Wick but he's still as good as he was.",
            "Chad Stahelski, David Leitch (uncredited)",
            "Derek Kolstad (screenplay)",
            "Keanu Reeves, Michael Nyqvist, Alfie Allen",
            "e2BnNqRpBshuvrypbpJw7Frp.jpg",
            "SakQnp8TLEMtv6NMrHFFZwLU.jpg",
            "3vyDeDhK65c5NthV6q34MKwQ.mp4",
            "3vyDeDhK65c5NthV6q34MKwQ.mp4");

    private Movie movie12 = new Movie(
            "Guardians of the Galaxy",
            2014,
            121,
            "A group of intergalactic criminals are forced to work together to stop a fanatical warrior from taking control of the universe.",
            "After stealing a mysterious orb in the far reaches of outer space, Peter Quill from Earth, is now the main target of a manhunt led by the villain known as Ronan the Accuser. To help fight Ronan and his team and save the galaxy from his power, Quill creates a team of space heroes known as the 'Guardians of the Galaxy' to save the world.",
            "James Gunn",
            "James Gunn, Nicole Perlman",
            "Chris Pratt, Vin Diesel, Bradley Cooper",
            "AvCeXp2SnZTPV5F6XvAs8Qsn.jpg",
            "DvMfAbh2b9PYfBjYgdj5bzyf.jpg",
            "QAjfpyseWB6W4KJ5ycMrcv4B.mp4",
            "QAjfpyseWB6W4KJ5ycMrcv4B.mp4");

    private Movie movie13 = new Movie(
            "Run All Night",
            2015,
            114,
            "Mobster and hit man Jimmy Conlon has one night to figure out where his loyalties lie: with his estranged son, Mike, whose life is in danger, or his longtime best friend, mob boss Shawn Maguire, who wants Mike to pay for the death of his own son.",
            "Professional Brooklyn hitman Jimmy Conlon is more commonly known as THE GRAVEDIGGER. Jimmy was a mob hit-man, who was best friends with his boss Sean Maguire. But when Jimmy's son, Michael, is marked for death by the mob, Jimmy must go up against Sean to protect Michael at all costs. Together, he and Michael must avoid corrupt cops, contract killers and the mob to survive the night.",
            "Jaume Collet-Serra",
            "Brad Ingelsby",
            "Liam Neeson, Ed Harris, Joel Kinnaman",
            "xN5dPL2UhAEEftRNgwqdJK8x.jpg",
            "7zfnnmHwxQn5pQKhJnmMzGfL.jpg",
            "hRmn8bf7VNayMbVdzgN9syYy.mp4",
            "hRmn8bf7VNayMbVdzgN9syYy.mp4");

    private Movie movie14 = new Movie(
            "The Maze Runner",
            2014,
            113,
            "Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow 'runners' for a shot at escape.",
            "Thomas wakes up in an elevator, remembering nothing but his own name. He emerges into a world of about 60 teen boys who have learned to survive in a completely enclosed environment, subsisting on their own agriculture and supplies. A new boy arrives every 30 days. The original group has been in 'The Glade' for three years, trying to find a way to escape through the Maze that surrounds their living space. They have begun to give up hope. Then a comatose girl arrives with a strange note, and their world begins to change. There are some great, fast-paced action scenes, particularly those involving the nightmarish Grievers who plague the boys.",
            "Wes Ball",
            "Noah Oppenheim (screenplay), Grant Pierce Myers (screenplay)",
            "Dylan O'Brien, Kaya Scodelario, Will Poulter",
            "eReawsqkdUGcqYQvN5BgumrA.jpg",
            "tZXjZgXkJGcG28h5sCtwq8yb.jpg",
            "ReCxsUwd9JzEhzXWc5aHw87d.mp4",
            "ReCxsUwd9JzEhzXWc5aHw87d.mp4");

    private Movie movie15 = new Movie(
            "Lucy",
            2014,
            89,
            "A woman, accidentally caught in a dark deal, turns the tables on her captors and transforms into a merciless warrior evolved beyond human logic.",
            "It was supposed to be a simple job. All Lucy had to do was deliver a mysterious briefcase to Mr. Jang. But immediately Lucy is caught up in a nightmarish deal where she is captured and turned into a drug mule for a new and powerful synthetic drug. When the bag she is carrying inside of her stomach leaks, Lucy's body undergoes unimaginable changes that begins to unlock her mind's full potential. With her new-found powers, Lucy turns into a merciless warrior intent on getting back at her captors. She receives invaluable help from Professor Norman, the leading authority on the human mind, and French police captain Pierre Del Rio.",
            "Luc Besson",
            "Luc Besson",
            "Scarlett Johansson, Morgan Freeman, Min-sik Choi",
            "dw2SLRZvVspyG69kKnUu75nA.jpg",
            "PJj6EYSXZLZcPUVTgdEy4whm.jpg",
            "pbfjQR7e4GtVcUz5yrF5tCKC.mp4",
            "pbfjQR7e4GtVcUz5yrF5tCKC.mp4");

    private Movie movie16 = new Movie(
            "The Equalizer",
            2014,
            132,
            "A man believes he has put his mysterious past behind him and has dedicated himself to beginning a new, quiet life. But when he meets a young girl under the control of ultra-violent Russian gangsters, he can't stand idly by - he has to help her.",
            "In The Equalizer, Denzel Washington plays McCall, a man who believes he has put his mysterious past behind him and dedicated himself to beginning a new, quiet life. But when McCall meets Teri (Chloë Grace Moretz), a young girl under the control of ultra-violent Russian gangsters, he can't stand idly by - he has to help her. Armed with hidden skills that allow him to serve vengeance against anyone who would brutalize the helpless, McCall comes out of his self-imposed retirement and finds his desire for justice reawakened. If someone has a problem, if the odds are stacked against them, if they have nowhere else to turn, McCall will help. He is The Equalizer.",
            "Antoine Fuqua",
            "Richard Wenk, Michael Sloan (television series)",
            "Denzel Washington, Marton Csokas, Chloë Grace Moretz",
            "tKb5h6VYcFjfLh3AYmR9qYTc.jpg",
            "bkJJ385tT9pLwrA4fndn4cfe.jpg",
            "4rtKhDpR6ccumUFSEteSqcam.mp4",
            "4rtKhDpR6ccumUFSEteSqcam.mp4");

    private Movie movie17 = new Movie(
            "Edge of Tomorrow",
            2014,
            113,
            "A military officer is brought into an alien war against an extraterrestrial enemy who can reset the day and know the future. When this officer is enabled with the same power, he teams up with a Special Forces warrior to try and end the war.",
            "An alien race has hit the Earth in an unrelenting assault, unbeatable by any military unit in the world. Major William Cage (Cruise) is an officer who has never seen a day of combat when he is unceremoniously dropped into what amounts to a suicide mission. Killed within minutes, Cage now finds himself inexplicably thrown into a time loop-forcing him to live out the same brutal combat over and over, fighting and dying again...and again. But with each battle, Cage becomes able to engage the adversaries with increasing skill, alongside Special Forces warrior Rita Vrataski (Blunt). And, as Cage and Vrataski take the fight to the aliens, each repeated encounter gets them one step closer to defeating the enemy!",
            "Doug Liman",
            "Christopher McQuarrie (screenplay), Jez Butterworth (screenplay)",
            "Tom Cruise, Emily Blunt, Bill Paxton",
            "vMyByEvtc6naN9ZWyvxfH5Rp.jpg",
            "T99DzjxpTnKJZP3sjcEdGu8V.jpg",
            "R88Jxzs7mnkpUBpPsYTwxbg5.mp4",
            "R88Jxzs7mnkpUBpPsYTwxbg5.mp4");

    private Movie movie18 = new Movie(
            "The Hunger Games: Mockingjay - Part 1",
            2014,
            123,
            "Katniss Everdeen is in District 13 after she shatters the games forever. Under the leadership of President Coin and the advice of her trusted friends, Katniss spreads her wings as she fights to save Peeta and a nation moved by her courage.",
            "With the Games destroyed, Katniss Everdeen, along with Gale, Finnick and Beetee, end up in the so thought 'destroyed' District 13. Under the leadership of President Coin and the advice of her friends, Katniss becomes the 'Mockingjay', the symbol of rebellion for the districts of Panem.",
            "Francis Lawrence",
            "Peter Craig (screenplay), Danny Strong (screenplay)",
            "Jennifer Lawrence, Josh Hutcherson, Liam Hemsworth",
            "K4a9MYp7kjvvAkAsSM3WuSME.jpg",
            "GYDsRrwREvwEFcK6mAm3U6kR.jpg",
            "chfCQf4wKDAWVEyS9nZZyrtW.mp4",
            "chfCQf4wKDAWVEyS9nZZyrtW.mp4");

    private Movie[] movies = new Movie[]{
            movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9,
            movie10, movie11, movie12, movie13, movie14, movie15, movie16, movie17, movie18
    };

    private String comment1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vitae metus inerat pellentesque tristique. Aliquam non tincidunt velit. Integer placerat luctusvelit, vitae lacinia nisi placerat id. Duis at sapien nulla. Duis scelerisque quamet diam semper, a commodo libero rhoncus. Fusce bibendum id nibh eu rhoncus. Nullacursus, libero at maximus aliquam, leo justo efficitur urna, ac elementum sem nunceu tellus. Nullam fringilla porta venenatis. In pharetra quam pretium ex suscipitluctus. Sed ornare varius tortor, ac ornare tellus tristique et. Aenean velultrices tortor. Morbi tempus commodo quam nec ultrices. Vestibulum at enim magna.Vivamus auctor semper libero varius rutrum. Ut vel fringilla nisi.";

    private String comment2 = "Proin congue tincidunt orci, fringilla maximus urna rutrum in. Duis elementumultrices scelerisque. Praesent ante est, vestibulum in nulla vel, dignissiminterdum nisl. Morbi ullamcorper odio porttitor, interdum lacus non, viverra mi.Ut ut congue libero. In fringilla orci ligula, mattis viverra lorem pulvinar sitamet. Vestibulum et mi massa.";

    private String comment3 = "Sed sit amet semper nisl. Proin eget lorem ut felis auctor rutrum in a lectus.Aenean eget lacinia elit. Nulla molestie risus a diam posuere, sit amet interdumipsum tempor. Nunc ac vehicula sem. Ut tincidunt libero leo, a accumsan nullaiaculis ac. Nunc volutpat tempor justo et pellentesque. Aenean lorem metus,hendrerit at velit sed, euismod porttitor eros. Curabitur elementum felis mauris,in condimentum mauris placerat eget. Aliquam pellentesque ipsum quis consecteturaliquet.";

    private String comment4 = "Praesent ultrices, nulla vitae consectetur imperdiet, nulla purus condimentum elit,ac facilisis nisi ligula commodo purus. Duis eget metus mi. Pellentesque habitantmorbi tristique senectus et netus et malesuada fames ac turpis egestas.Pellentesque quis efficitur lectus. In aliquam arcu mi, lacinia pretium quamposuere at. Praesent molestie, sapien eu lacinia tristique, enim nunc aliquetlacus, et porttitor ligula dui id felis. Sed risus mauris, scelerisque vel sapienet, mattis cursus est. In ornare tellus nulla, at dignissim libero porta id. Cumsociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.Sed sed feugiat dui. Maecenas ultrices ipsum ac dignissim rutrum. Proin consequatmassa metus, et pretium metus gravida vitae. Donec purus dolor, ultricies euismodtincidunt sed, luctus id quam. Mauris hendrerit tristique ante, posuere vulputatenisl gravida sed. Aliquam sit amet risus quis ligula vulputate semper. Aeneanvolutpat tellus nec nunc consequat, eget feugiat tortor placerat.";

    private String comment5 = "Proin non felis at tellus consequat viverra at ut nisl. In laoreet, tellus idfeugiat maximus, libero dolor sagittis purus, vel dignissim libero erat quisturpis. Vestibulum tincidunt lobortis eleifend. Sed sit amet rutrum leo, quisluctus metus. Morbi rhoncus mauris vel auctor consequat. Vestibulum ante ipsumprimis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut sed venenatisturpis. Proin fermentum tellus ac congue tristique. Sed luctus porttitor nisi, utlaoreet elit congue egestas";

    private void relationsMovieGenre() {
        movie1.addGenre(action);
        movie1.addGenre(adventure);
        movie1.addGenre(fantasy);

        movie2.addGenre(action);
        movie2.addGenre(adventure);
        movie2.addGenre(comedy);

        movie3.addGenre(action);
        movie3.addGenre(scifi);
        movie3.addGenre(thriller);

        movie4.addGenre(animation);
        movie4.addGenre(action);
        movie4.addGenre(adventure);

        movie5.addGenre(action);
        movie5.addGenre(biography);
        movie5.addGenre(drama);

        movie6.addGenre(action);
        movie6.addGenre(adventure);
        movie6.addGenre(drama);

        movie7.addGenre(action);
        movie7.addGenre(adventure);
        movie7.addGenre(scifi);

        movie8.addGenre(action);
        movie8.addGenre(thriller);

        movie9.addGenre(action);
        movie9.addGenre(crime);
        movie9.addGenre(thriller);

        movie10.addGenre(action);
        movie10.addGenre(drama);
        movie10.addGenre(war);

        movie11.addGenre(action);
        movie11.addGenre(thriller);

        movie12.addGenre(action);
        movie12.addGenre(adventure);
        movie12.addGenre(scifi);

        movie13.addGenre(action);
        movie13.addGenre(crime);
        movie13.addGenre(drama);

        movie14.addGenre(action);
        movie14.addGenre(mystery);
        movie14.addGenre(scifi);

        movie15.addGenre(action);
        movie15.addGenre(scifi);
        movie15.addGenre(thriller);

        movie16.addGenre(action);
        movie16.addGenre(crime);
        movie16.addGenre(thriller);

        movie17.addGenre(action);
        movie17.addGenre(scifi);

        movie18.addGenre(adventure);
        movie18.addGenre(scifi);
    }


}
