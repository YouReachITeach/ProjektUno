import { View, Text, StyleSheet, FlatList, ActivityIndicator } from 'react-native';
import { useEffect, useState } from 'react';
import { useRouter } from 'expo-router';
import { TouchableOpacity } from 'react-native';


// 🔧 Spielertypen definieren – du kannst je nach Backendstruktur anpassen
type Player = {
  id: number;
  name: string;
  playerType: string;
  points?: number;
  // Weitere optionale Felder aus Unterklassen hier hinzufügen:
  spikes?: number;
  blocks?: number;
  digs?: number;
};

export default function TeamScreen() {
  const [players, setPlayers] = useState<Player[]>([]);
  const [loading, setLoading] = useState(true);

  const userId = 1; // 🔧 Aktuell Testnutzer – später dynamisch

  useEffect(() => {
    fetch(`http://192.168.198.1:8080/api/team/byUser/1`)
  .then(res => res.json())
  .then(data => {
    console.log("RESPONSE VOM BACKEND:", data);
    setPlayers(data.players || []); // nur wenn players direkt da ist
    setLoading(false);
  })

      .catch(err => {
        console.error('Fehler beim Laden:', err);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#ff9500" />
        <Text>Lade dein Team...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>🏐 Mein Team</Text>
      <FlatList
        data={players}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <Text style={styles.name}>{item.name}</Text>
            <Text style={styles.role}>Typ: {item.playerType ?? 'Unbekannt'}</Text>
            <Text style={styles.points}>
              Punkte: {item.points !== undefined ? item.points : '–'}
            </Text>
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
    backgroundColor: '#f1f1f1',
    marginBottom: 12,
    borderRadius: 8,
  },
  name: { fontSize: 18, fontWeight: 'bold' },
  role: { fontSize: 14, color: '#555' },
  points: { fontSize: 14, color: '#111' },
});
