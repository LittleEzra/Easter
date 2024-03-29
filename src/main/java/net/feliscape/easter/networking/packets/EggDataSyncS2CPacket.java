package net.feliscape.easter.networking.packets;

import net.feliscape.easter.client.ClientEggData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EggDataSyncS2CPacket {
    private final int easterEggs;
    private final int goldenEggs;

    public EggDataSyncS2CPacket(int easterEggs, int goldenEggs){
        this.easterEggs = easterEggs;
        this.goldenEggs = goldenEggs;
    }

    public EggDataSyncS2CPacket(FriendlyByteBuf buf){
        this.easterEggs = buf.readInt();
        this.goldenEggs = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(easterEggs);
        buf.writeInt(goldenEggs);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Client-side
            ClientEggData.set(easterEggs, goldenEggs);
        });
        return true;
    }
}
