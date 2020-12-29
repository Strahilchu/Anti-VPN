package me.egg82.antivpn.messaging.packets;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

public class PlayerPacket extends AbstractPacket {
    private UUID uuid;
    private boolean value;

    public byte getPacketId() { return 0x02; }

    public PlayerPacket(ByteBuffer data) { read(data); }

    public PlayerPacket() {
        this.uuid = null;
        this.value = false;
    }

    public void read(ByteBuffer buffer) {
        if (!checkVersion(buffer)) {
            return;
        }

        this.uuid = getUUID(buffer);
        this.value = getBoolean(buffer);

        checkReadPacket(buffer);
    }

    public void write(ByteBuffer buffer) {
        buffer.put(VERSION);

        putUUID(this.uuid, buffer);
        putBoolean(this.value, buffer);
    }

    public UUID getUuid() { return uuid; }

    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public boolean getValue() { return value; }

    public void setValue(boolean value) { this.value = value; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPacket)) return false;
        PlayerPacket that = (PlayerPacket) o;
        return value == that.value && Objects.equals(uuid, that.uuid);
    }

    public int hashCode() { return Objects.hash(uuid, value); }

    public String toString() {
        return "PlayerPacket{" +
                "uuid=" + uuid +
                ", value=" + value +
                '}';
    }
}