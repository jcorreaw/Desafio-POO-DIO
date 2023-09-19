package phoneApp.objects;

import java.util.Date;

public class VideoCall extends Callable {
    public VideoCall(Contact caller, Contact recipient, double duration, Date date) {
        super(caller, recipient, duration, date);
    }

    @Override
    public Callable end() {
        if(callStart != 0 )this.setDuration( (System.currentTimeMillis() - callStart)/1000 );
        System.out.println(MSG_END.concat(" de vídeo."));
        return this;
    }
}
