package phoneApp.listeners;

import phoneApp.objects.Callable;
import phoneApp.objects.VoiceMail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CallListener {
    private List<Callable> activeCalls;
    private List<Callable> newCalls;
    private List<VoiceMail> voiceMails;

    public CallListener() {
        activeCalls = new ArrayList<>();
        newCalls = new ArrayList<>();
        voiceMails = new ArrayList<>();

        voiceMails.add(new VoiceMail("(24)3587-4347","Responda essa mensagem com PROMOÇÃO, e veja as promoções diponíveis!"));
        voiceMails.add(new VoiceMail("(11)4578-8495","Oi, esse número é do Roberto?"));
        voiceMails.add(new VoiceMail("(11)3647-1098","Responda essa mensagem com OI, e ganhe 1 mês de internet grátis!"));
    }

    public void startCall(Callable call) throws InterruptedException{
        if(call.getRecipient().getName()!= null) {
            System.out.println("Ligando para "+call.getRecipient().getName());
        }
        else {
            System.out.println("Ligando para "+call.getRecipient().getNumber());
        }
        TimeUnit.SECONDS.sleep((long) (Math.random()*3));
        activeCalls.add(call);
        call.start();
    }

    public void acceptCall(Callable call) throws InterruptedException{
        if(call.getCaller().getName()!= null) {
            System.out.println("Recebendo ligação de "+call.getCaller().getName());
        }
        else {
            System.out.println("Recebendo ligação de "+call.getCaller().getNumber());
        }
        TimeUnit.SECONDS.sleep((long) (Math.random()*3));
        activeCalls.add(call);
        call.accept();
    }

    public void declineCall(Callable call){
        call.setStatus(false);
        call.end();
    }

    public Callable endCall(Callable call){
        call.setStatus(true);
        activeCalls.remove(call);
        newCalls.remove(call);
        call.end();
        return call;
    }

    public void addNewCall(Callable call){
        newCalls.add(call);
    }

    public List<VoiceMail> getVoiceMailByNumber(String number){
        System.out.println("Procurando mensagens do número: "+number);
        return voiceMails.stream().filter(vm -> (vm.getNumber().equals(number))).toList();
    }


    public List<Callable> getActiveCalls() {
        return activeCalls;
    }

    public void setActiveCalls(List<Callable> activeCalls) {
        this.activeCalls = activeCalls;
    }

    public List<Callable> getNewCalls() {
        return newCalls;
    }

    public List<VoiceMail> getVoiceMails() {
        return voiceMails;
    }

    public void setVoiceMails(List<VoiceMail> voiceMails) {
        this.voiceMails = voiceMails;
    }
}
