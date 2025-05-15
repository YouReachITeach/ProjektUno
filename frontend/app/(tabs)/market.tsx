import { View, Text, FlatList, StyleSheet, ActivityIndicator, TouchableOpacity, Alert } from 'react-native';
import { useEffect, useState } from 'react';

type Player = {
  id: number;
  name: string;
  playerType: string;
  price: number;
};

export default function MarketScreen() {
  const [players, setPlayers] = useState<Player[]>([]);
  const [loading, setLoading] = useState(true);

  const teamId = 1; // 🔧 Test-Team-ID – später dynamisch machen

  const fetchPlayers = () => {
    setLoading(true);
    fetch('http://192.168.198.1:8080/api/player/available')
      .then(res => res.json())
      .then(data => {
        setPlayers(data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Fehler beim Laden:", err);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPlayers();
  }, []);

  const handleBuy = (playerId: number) => {
    fetch(`http://192.168.198.1:8080/api/team/${teamId}/buyPlayer/${playerId}`, {
      method: 'POST',
    })
      .then(res => {
        if (res.ok) {
          Alert.alert("✅ Erfolg", "Spieler wurde gekauft!");
          fetchPlayers(); // Liste aktualisieren
        } else {
          Alert.alert("❌ Fehler", "Nicht genug Budget oder Spieler gehört bereits zu einem Team.");
        }
      })
      .catch(() => {
        Alert.alert("❌ Fehler", "Kauf fehlgeschlagen (Netzwerkfehler).");
      });
  };

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#ff9500" />
        <Text>Lade Transfermarkt...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>🛒 Transfermarkt</Text>
      <FlatList
        data={players}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <Text style={styles.name}>{item.name}</Text>
            <Text style={styles.role}>Position: {item.playerType}</Text>
            <Text style={styles.price}>💰 Preis: {item.price} €</Text>

            <TouchableOpacity style={styles.button} onPress={() => handleBuy(item.id)}>
              <Text style={styles.buttonText}>Spieler kaufen</Text>
            </TouchableOpacity>
          </View>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#fff' },
  title: { fontSize: 24, fontWeight: 'bold', marginBottom: 20, textAlign: 'center' },
  card: {
    padding: 15,
    backgroundColor: '#f9f9f9',
    borderRadius: 10,
    marginBottom: 12,
  },
  name: { fontSize: 18, fontWeight: 'bold' },
  role: { fontSize: 14, color: '#555' },
  price: { fontSize: 14, color: '#111', marginBottom: 8 },
  button: {
    backgroundColor: '#007aff',
    paddingVertical: 8,
    borderRadius: 6,
    alignItems: 'center',
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
});
